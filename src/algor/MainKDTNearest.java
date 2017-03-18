package algor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by tieorange on 18/03/2017.
 */
public class MainKDTNearest {

    public static void main(String args[]) throws IOException {
//        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        int numpoints = 5;

        KDTree kdTree = new KDTree(numpoints);
        double x[] = new double[2];

        double[][] matrxi = new double[2][2]; // MATRIX

        x[0] = 2.1;
        x[1] = 4.3;
        kdTree.add(x);

        x[0] = 3.3;
        x[1] = 1.5;
        kdTree.add(x);

        x[0] = 4.7;
        x[1] = 11.1;
        kdTree.add(x);

        x[0] = 5.0;
        x[1] = 12.3;
        kdTree.add(x);

        x[0] = 5.1;
        x[1] = 1.2;
        kdTree.add(x);

        System.out
                .println("Enter the co-ordinates of the point: (one after the other)");
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        double sx = Double.parseDouble(br.readLine());
        double sy = Double.parseDouble(br.readLine());

        double userInputArray[] = {sx, sy};
        KDNode kdNode = kdTree.find_nearest(userInputArray);
        System.out.println("The nearest neighbor is: ");
        System.out.println("(" + kdNode.x[0] + " , " + kdNode.x[1] + ")");
//        in.close();
    }
}