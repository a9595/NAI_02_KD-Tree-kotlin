package algor

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

/**
 * Created by tieorange on 18/03/2017.
 */

object FileReader {
    var columnsToReadCount = 4 // don't read last column (name)

    @Throws(IOException::class)
    @JvmStatic fun main(args: Array<String>) {
        getKDTreeWithFileData()
    }

    fun getKDTreeWithFileData(): KDTree {
        val linesList = BufferedReader(FileReader("train.txt")).readLines()
        val kdTree = KDTree(linesList.size)

        for (line in linesList) {
            var entry = DoubleArray(columnsToReadCount)
            val splitList = line.split(",")
            for (i in 0..columnsToReadCount-1) {
                entry[i] = splitList[i].toDouble()
            }
            kdTree.add(entry)
        }

        return kdTree
    }


}