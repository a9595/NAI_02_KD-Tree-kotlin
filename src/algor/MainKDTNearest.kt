package algor

import algor.FileReader.getDataArray
import java.io.IOException

/**
 * Created by tieorange on 18/03/2017.
 */
object MainKDTNearest {

    @Throws(IOException::class)
    @JvmStatic fun main(args: Array<String>) {
        val trainingData = getDataArray("train.txt")
        val kdTree = KDTree(trainingData)
        val testDataArray = getDataArray("test.txt")

        performTestData(testDataArray, kdTree)
    }

    private fun performTestData(testDataArray: List<DoubleArray>, kdTree: KDTree) {
        var correctAnswers = 0
        for (testDataRow in testDataArray) {
            val kdNode = kdTree.find_nearest(testDataRow)
            val isCorrect = kdNode?.printNeighbor(testDataRow) ?: false
            if (isCorrect) correctAnswers++
        }

        val accuracyRate = (correctAnswers / testDataArray.size.toFloat()) * 100.0
        println("accuracyRate = $accuracyRate")
    }

    fun getUserInput() {
        /* println("Enter the co-ordinates of the point: (one after the other)")
         val reader = InputStreamReader(System.`in`)
         val br = BufferedReader(reader)
         val xUserInput = br.readLine().toDouble()
         val yUserInput = br.readLine().toDouble()*/
    }

}