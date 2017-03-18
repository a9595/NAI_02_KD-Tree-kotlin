package algor

import algor.FileReader.getKDTreeWithFileData
import java.io.IOException

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

        val userInputArray = doubleArrayOf(5.6, 3.0, 4.1, 1.3)
        val kdNode = kdTree.find_nearest(userInputArray)
        kdNode?.printNearestN()
    }

}