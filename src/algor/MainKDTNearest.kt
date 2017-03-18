package algor

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by tieorange on 18/03/2017.
 */
object MainKDTNearest {

    @Throws(IOException::class)
    @JvmStatic fun main(args: Array<String>) {
//                BufferedReader in = new BufferedReader(new FileReader("input.txt"));
//        readDataFromFile()

        val numpoints = 5

        val kdTree = KDTree(numpoints)
        val x = DoubleArray(2)

        x[0] = 2.1
        x[1] = 4.3
        kdTree.add(x)

        x[0] = 3.3
        x[1] = 1.5
        kdTree.add(x)

        x[0] = 4.7
        x[1] = 11.1
        kdTree.add(x)

        x[0] = 5.0
        x[1] = 12.3
        kdTree.add(x)

        x[0] = 5.1
        x[1] = 1.2
        kdTree.add(x)

        println("Enter the co-ordinates of the point: (one after the other)")
        val reader = InputStreamReader(System.`in`)
        val br = BufferedReader(reader)
        val xUserInput = java.lang.Double.parseDouble(br.readLine())
        val yUserInput = java.lang.Double.parseDouble(br.readLine())

        val userInputArray = doubleArrayOf(xUserInput, yUserInput)
        val kdNode = kdTree.find_nearest(userInputArray)
        println("The nearest neighbor is: ")
        if (kdNode != null) {
            println("(" + kdNode.x[0] + " , " + kdNode.x[1] + ")")
        }
        //        in.close();
    }
}