package algor

import algor.FileReader.COLUMNS_COUNT

/**
 * Created by tieorange on 18/03/2017.
 */
class KDTree {
    private val LIMIT = 10

    constructor(data: List<DoubleArray>) {
        val size = data.size
        this.d_min = 0.toDouble()
        root = null
        kd_id = 1
        nList = 0
        listNodes = arrayOfNulls<KDNode>(size)
        CheckedNodes = arrayOfNulls<KDNode>(size)
        max_boundary = BooleanArray(columnsCount)
        min_boundary = BooleanArray(columnsCount)
        x_min = DoubleArray(columnsCount)
        x_max = DoubleArray(columnsCount)

        addAll(data)
    }

    internal var root: KDNode? = null

    internal var TimeStart: Int = 0
    internal var TimeFinish: Int = 0
    internal var CounterFreq: Int = 0

    internal var d_min: Double
    internal var nearest_neighbour: KDNode? = null

    internal var kd_id: Int = 0

    internal var nList: Int = 0

    internal var CheckedNodes: Array<KDNode?>
    internal var checked_nodes: Int = 0
    internal var listNodes: Array<KDNode?>

    internal var x_min: DoubleArray
    internal var x_max: DoubleArray
    internal var max_boundary: BooleanArray
    internal var min_boundary: BooleanArray
    internal var n_boundary: Int = 0
    val columnsCount = COLUMNS_COUNT


    fun add(dataArray: DoubleArray): Boolean {
        if (nList >= LIMIT)
            return false // can't add more points

        if (root == null) {
            root = KDNode(dataArray, 0)
            root!!.id = kd_id++
            listNodes[nList++] = root
        } else {
            val pNode: KDNode? = root!!.insert(dataArray)
            if (pNode != null) {
                pNode.id = kd_id++
                listNodes[nList++] = pNode
            }
        }

        return true
    }

    fun addAll(listData: List<DoubleArray>) {
        for (dataItem in listData) {
            add(dataItem)
        }
    }

    fun find_nearest(userInputArray: DoubleArray): KDNode? {
        if (root == null)
            return null

        checked_nodes = 0
        val parent = root!!.findParent(userInputArray)
        nearest_neighbour = parent
        if (parent != null) {
            d_min = root!!.distance2(userInputArray, parent.data, columnsCount)

            if (parent.equal(userInputArray, parent.data, columnsCount))
                return nearest_neighbour
        }

        search_parent(parent, userInputArray)
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
        val d = node.data[dim] - x[dim]

        if (d * d > d_min) {
            if (node.data[dim] > x[dim])
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
        for (k in 0..columnsCount - 1) {
            dx = node.data[k] - x[k]
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
        for (k in 0..columnsCount - 1) {
            x_max[k] = 0.0
            x_min[k] = x_max[k]
            min_boundary[k] = false
            max_boundary[k] = min_boundary[k] //
        }
        n_boundary = 0

        var search_root = parent
        while (parent != null && n_boundary != columnsCount * columnsCount) {
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