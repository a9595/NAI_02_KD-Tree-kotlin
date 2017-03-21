package algor

import algor.FileReader.getKDTreeWithTrainData
import algor.FileReader.getTestDataArray
import java.io.IOException

/**
 * Created by tieorange on 18/03/2017.
 */
object MainKDTNearest {

    @Throws(IOException::class)
    @JvmStatic fun main(args: Array<String>) {
        val kdTree = getKDTreeWithTrainData()
        val testDataArray = getTestDataArray()

        performTestData(testDataArray, kdTree)
    }

    private fun performTestData(testDataArray: List<DoubleArray>, kdTree: KDTree) {
        for (testDataRow in testDataArray) {
            val kdNode = kdTree.find_nearest(testDataRow)
            kdNode?.printNeighbor(testDataRow)
        }
    }

    fun getUserInput() {
        /* println("Enter the co-ordinates of the point: (one after the other)")
         val reader = InputStreamReader(System.`in`)
         val br = BufferedReader(reader)
         val xUserInput = br.readLine().toDouble()
         val yUserInput = br.readLine().toDouble()*/
    }

}