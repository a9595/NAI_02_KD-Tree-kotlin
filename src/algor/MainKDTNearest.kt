package algor

import algor.FileReader.getKDTreeWithFileData
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by tieorange on 18/03/2017.
 */
object MainKDTNearest {

    @Throws(IOException::class)
    @JvmStatic fun main(args: Array<String>) {
        val kdTree = getKDTreeWithFileData()

        /* println("Enter the co-ordinates of the point: (one after the other)")
         val reader = InputStreamReader(System.`in`)
         val br = BufferedReader(reader)
         val xUserInput = br.readLine().toDouble()
         val yUserInput = br.readLine().toDouble()*/

        val userInputArray = doubleArrayOf(5.7, 2.5, 5.0, 2.0)
        val kdNode = kdTree.find_nearest(userInputArray)
        kdNode?.printNearestN()
    }

}