package algor

/**
 * Created by tieorange on 18/03/2017.
 */
class KDTree(i: Int) {
    internal var Root: KDNode? = null

    internal var TimeStart: Int = 0
    internal var TimeFinish: Int = 0
    internal var CounterFreq: Int = 0

    internal var d_min: Double = 0.toDouble()
    internal var nearest_neighbour: KDNode? = null

    internal var KD_id: Int = 0

    internal var nList: Int = 0

    internal var CheckedNodes: Array<KDNode?>
    internal var checked_nodes: Int = 0
    internal var List: Array<KDNode?>

    internal var x_min: DoubleArray
    internal var x_max: DoubleArray
    internal var max_boundary: BooleanArray
    internal var min_boundary: BooleanArray
    internal var n_boundary: Int = 0

    init {
        val size = 2
        Root = null
        KD_id = 1
        nList = 0
        List = arrayOfNulls<KDNode>(i)
        CheckedNodes = arrayOfNulls<KDNode>(i)
        max_boundary = BooleanArray(size)
        min_boundary = BooleanArray(size)
        x_min = DoubleArray(size)
        x_max = DoubleArray(size)
    }

    fun add(x: DoubleArray): Boolean {
        if (nList >= 2000000 - 1)
            return false // can't add more points

        if (Root == null) {
            Root = KDNode(x, 0)
            Root!!.id = KD_id++
            List[nList++] = Root
        } else {
            val pNode: KDNode? = Root!!.Insert(x)
            if (pNode != null) {
                pNode.id = KD_id++
                List[nList++] = pNode
            }
        }

        return true
    }

    fun find_nearest(x: DoubleArray): KDNode? {
        if (Root == null)
            return null

        checked_nodes = 0
        val parent = Root!!.FindParent(x)
        nearest_neighbour = parent
        if (parent != null) {
            d_min = Root!!.distance2(x, parent.x, 2)

            if (parent.equal(x, parent.x, 2))
                return nearest_neighbour
        }

        search_parent(parent, x)
        uncheck()

        return nearest_neighbour
    }

    fun check_subtree(node: KDNode?, x: DoubleArray) {
        if (node == null || node.checked)
            return

        CheckedNodes[checked_nodes++] = node
        node.checked = true
        set_bounding_cube(node, x)

        val dim = node.axis
        val d = node.x[dim] - x[dim]

        if (d * d > d_min) {
            if (node.x[dim] > x[dim])
                check_subtree(node.Left, x)
            else
                check_subtree(node.Right, x)
        } else {
            check_subtree(node.Left, x)
            check_subtree(node.Right, x)
        }
    }

    fun set_bounding_cube(node: KDNode?, x: DoubleArray) {
        if (node == null)
            return
        var d = 0
        var dx: Double
        for (k in 0..1) {
            dx = node.x[k] - x[k]
            if (dx > 0) {
                dx *= dx
                if (!max_boundary[k]) {
                    if (dx > x_max[k])
                        x_max[k] = dx
                    if (x_max[k] > d_min) {
                        max_boundary[k] = true
                        n_boundary++
                    }
                }
            } else {
                dx *= dx
                if (!min_boundary[k]) {
                    if (dx > x_min[k])
                        x_min[k] = dx
                    if (x_min[k] > d_min) {
                        min_boundary[k] = true
                        n_boundary++
                    }
                }
            }
            d += dx.toInt()
            if (d > d_min)
                return

        }

        if (d < d_min) {
            d_min = d.toDouble()
            nearest_neighbour = node
        }
    }

    fun search_parent(parent: KDNode?, x: DoubleArray): KDNode? {
        var parent = parent
        for (k in 0..1) {
            x_max[k] = 0.0
            x_min[k] = x_max[k]
            min_boundary[k] = false
            max_boundary[k] = min_boundary[k] //
        }
        n_boundary = 0

        var search_root = parent
        while (parent != null && n_boundary != 2 * 2) {
            check_subtree(parent, x)
            search_root = parent
            parent = parent.Parent
        }

        return search_root
    }

    fun uncheck() {
        for (n in 0..checked_nodes - 1)
            CheckedNodes[n]?.checked = false
    }

}