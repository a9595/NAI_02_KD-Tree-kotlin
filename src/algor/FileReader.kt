package algor

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

/**
 * Created by tieorange on 18/03/2017.
 */

object FileReader {
    @Throws(IOException::class)
    @JvmStatic fun main(args: Array<String>) {
        readDataFromFile()
    }

    fun readDataFromFile() {
        val readLines = BufferedReader(FileReader("train.txt")).readLines()

        val splitList = readLines.first().split(",")
        print(splitList)

        for (line in readLines) {
//            line.split(',')
        }
    }


}