package algor

import algor.FileReader.COLUMNS_COUNT

/**
 * Created by tieorange on 18/03/2017.
 */

class KDNode(dataArray: DoubleArray, internal var axis: Int) {
    internal var data: DoubleArray
    internal var id: Int = 0
    internal var checked: Boolean = false
    internal var orientation: Boolean = false

    internal var Parent: KDNode? = null
    internal var Left: KDNode?
    internal var Right: KDNode?

    val columnsCount = COLUMNS_COUNT

    init {
        data = DoubleArray(columnsCount)
        for (k in 0..columnsCount - 1)
            data[k] = dataArray[k]

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
            if (x0[split] > next.data[split])
                next = next.Right
            else
                next = next.Left
        }
        return parent
    }

    fun Insert(p: DoubleArray): KDNode? {
        //data = new double[2];
        val parent = FindParent(p)
        if (parent != null) {
            if (equal(p, parent.data, columnsCount))
                return null
        }

        val newNode = KDNode(p, if (parent!!.axis + 1 < columnsCount)
            parent.axis + 1
        else
            0)
        newNode.Parent = parent

        if (p[parent.axis] > parent.data[parent.axis]) {
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

    fun printNeighbor(testDataRow: DoubleArray): Boolean {
        val xString = arrayToString(data)
        val testString = arrayToString(testDataRow)

        val flowerTrainingSet = FileReader.getFlowerByRow(data, true)
        val flowerTestSet = FileReader.getFlowerByRow(testDataRow, false)
        val isCorrectFlower = flowerTestSet == flowerTrainingSet

        println("$isCorrectFlower Neighbor of: $testString - $flowerTestSet   " +
                "\n\t\t is: $xString - $flowerTrainingSet \n")
        return isCorrectFlower
    }

    fun arrayToString(testDataRow: DoubleArray) = testDataRow.joinToString(transform = Double::toString)
}
