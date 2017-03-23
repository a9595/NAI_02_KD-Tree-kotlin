package algor

import algor.FileReader.COLUMNS_COUNT
import algor.FileReader.getDataArray
import algor.FileReader.splitDataRow
import java.io.IOException

/**
 * Created by tieorange on 18/03/2017.
 */
object MainKDTNearest {

    private val trainingData: List<DoubleArray> = getDataArray("train.txt")

    private val testDataArray: List<DoubleArray> = getDataArray("test.txt")

    @Throws(IOException::class)
    @JvmStatic fun main(args: Array<String>) {
        val kdTree = KDTree(trainingData)

        performTestData(testDataArray, kdTree)

        askUserForInput(kdTree)
    }

    private fun askUserForInput(kdTree: KDTree) {
        print("Type in data (for ex. 5.7,2.5,5.0,2.0,Iris-virginica): ")
        val line = readLine()
        if (line != null) {
//            COLUMNS_COUNT = 5
            val splitDataRow = splitDataRow(line)
//            COLUMNS_COUNT = 4
            performTestData(listOf(splitDataRow), kdTree)
        }
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