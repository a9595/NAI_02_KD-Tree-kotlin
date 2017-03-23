package algor

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.*

/**
 * Created by tieorange on 18/03/2017.
 */

object FileReader {
    var COLUMNS_COUNT = 4 // don't read last column (name)
    const val IrisSetosa = 999.0
    const val IrisVersicolor = 998.0
    const val IrisVirginica = 997.0
    var trainingDataCheckArray = listOf<DoubleArray>()
    var testDataCheckArray: List<DoubleArray>

    init {
        COLUMNS_COUNT = 5
        trainingDataCheckArray = getDataArray("train.txt")
        testDataCheckArray = getDataArray("test.txt")
        COLUMNS_COUNT = 4
    }

    @Throws(IOException::class)
    @JvmStatic fun main(args: Array<String>) {
    }

    fun getDataArray(fileName: String): List<DoubleArray> {
        val result = ArrayList<DoubleArray>()
        val linesList = BufferedReader(FileReader(fileName)).readLines()

        for (line in linesList) {
            var entry = DoubleArray(COLUMNS_COUNT)
            val splitList = line.split(",")
            for (i in 0..COLUMNS_COUNT - 1) {
                entry[i] = when (splitList[i]) {
                    "Iris-virginica" -> IrisVirginica
                    "Iris-versicolor" -> IrisVersicolor
                    "Iris-setosa" -> IrisSetosa
                    else -> splitList[i].toDouble()
                }
            }
            result.add(entry)
        }

        return result
    }

    fun getTestDataArray(): List<DoubleArray> {
        val result = ArrayList<DoubleArray>()

        val linesList = BufferedReader(FileReader("test.txt")).readLines()

        for (line in linesList) {
            var entry = DoubleArray(COLUMNS_COUNT)
            val splitList = line.split(",")
            for (i in 0..COLUMNS_COUNT - 1) {
                entry[i] = splitList[i].toDouble()
            }
            result.add(entry)
        }
        return result
    }

    fun getFlowerByRow(data: DoubleArray, isTrainingSet: Boolean): String {
        var flowerId: Double = -1.0
        if (isTrainingSet) {
            for (doubles in trainingDataCheckArray) {
                val rowWithoutLastElement = doubleArrayOf(doubles[0], doubles[1], doubles[2], doubles[3])
                val equal = Arrays.equals(data, rowWithoutLastElement)
                if (equal) flowerId = doubles.last()
            }
        } else {
            for (doubles in testDataCheckArray) {
                val rowWithoutLastElement = doubleArrayOf(doubles[0], doubles[1], doubles[2], doubles[3])
                val equal = Arrays.equals(data, rowWithoutLastElement)
                if (equal) flowerId = doubles.last()
            }
        }
        return getFlowerById(flowerId)
    }

    fun getFlowerById(id: Double): String {
        return when (id) {
            IrisVirginica -> "Iris-virginica"
            IrisVersicolor -> "Iris-versicolor"
            IrisSetosa -> "Iris-setosa"
            else -> "Unknown type :("
        }
    }

    fun getDataLastRows(fileName: String): ArrayList<String> {
        val resultList = arrayListOf<String>()
        val linesList = BufferedReader(FileReader(fileName)).readLines()

        for (line in linesList) {
            val splitList = line.split(",")
            val lastRow = splitList.last()
            resultList.add(lastRow)
        }

        return resultList
    }


}
