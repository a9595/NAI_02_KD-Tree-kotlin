package algor

/**
 * Created by tieorange on 18/03/2017.
 */

class KDNode(x0: DoubleArray, internal var axis: Int) {
    internal var x: DoubleArray
    internal var id: Int = 0
    internal var checked: Boolean = false
    internal var orientation: Boolean = false

    internal var Parent: KDNode? = null
    internal var Left: KDNode?
    internal var Right: KDNode?

    init {
        x = DoubleArray(2)
        for (k in 0..1)
            x[k] = x0[k]

        Parent = null
        Right = Parent
        Left = Right
        checked = false
        id = 0
    }

    fun FindParent(x0: DoubleArray): KDNode? {
        var parent: KDNode? = null
        var next: KDNode? = this
        var split: Int
        while (next != null) {
            split = next.axis
            parent = next
            if (x0[split] > next.x[split])
                next = next.Right
            else
                next = next.Left
        }
        return parent
    }

    fun Insert(p: DoubleArray): KDNode? {
        //x = new double[2];
        val parent = FindParent(p)
        if (parent != null) {
            if (equal(p, parent.x, 2))
                return null
        }

        val newNode = KDNode(p, if (parent!!.axis + 1 < 2)
            parent.axis + 1
        else
            0)
        newNode.Parent = parent

        if (p[parent.axis] > parent.x[parent.axis]) {
            parent.Right = newNode
            newNode.orientation = true //
        } else {
            parent.Left = newNode
            newNode.orientation = false //
        }

        return newNode
    }

    internal fun equal(x1: DoubleArray, x2: DoubleArray, dim: Int): Boolean {
        for (k in 0..dim - 1) {
            if (x1[k] != x2[k])
                return false
        }

        return true
    }

    internal fun distance2(x1: DoubleArray, x2: DoubleArray, dim: Int): Double {
        var S = 0.0
        for (k in 0..dim - 1)
            S += (x1[k] - x2[k]) * (x1[k] - x2[k])
        return S
    }
}
