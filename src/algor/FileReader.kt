package algor

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

/**
 * Created by tieorange on 18/03/2017.
 */

object FileReader {
    const val COLUMNS_COUNT = 4 // don't read last column (name)

    @Throws(IOException::class)
    @JvmStatic fun main(args: Array<String>) {
        getKDTreeWithTrainData()
    }

    fun getKDTreeWithTrainData(): KDTree {
        val linesList = BufferedReader(FileReader("train.txt")).readLines()
        val kdTree = KDTree(linesList.size)

        for (line in linesList) {
            var entry = DoubleArray(COLUMNS_COUNT)
            val splitList = line.split(",")
            for (i in 0..COLUMNS_COUNT - 1) {
                entry[i] = splitList[i].toDouble()
            }
            kdTree.add(entry)
        }

        return kdTree
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

    fun getDataLastRows(fileName:String): ArrayList<String> {
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