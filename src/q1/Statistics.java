package q1;

import java.util.Scanner;

/**
 * <p>Computes and prints the mean and standard deviation of a list
 * of integers. No more than 50 valid integer input values are accepted</p>
 *
 * @author Jia Qi Lee
 * @version 1.0
 */
public class Statistics {
    /**
     * <p>This is the main method (entry point) that gets called by the JVM.</p>
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        final int fifty = 50;
        int[] stats = new int[fifty];
        double summation = 0;
        double sumMean = 0;
        int n;
        double mean;
        double sDeviation;
        
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter(", *");
        
        //Scans the input and stores the integers into the stats array and
        //calculates the summation of the values and the mean.
        for (n = 0; scan.hasNextInt(); n++) {
            stats[n] = scan.nextInt();
            summation += stats[n];
        }
        mean = summation / n;
        
        //Calculates the standard deviation and prints the results of the
        //mean and the standard deviation.
        for (int i = 0; i < n; i++) {
            sumMean += Math.pow(mean - stats[i], 2);
        }
        sDeviation = Math.sqrt(sumMean / (n - 1));
        
        System.out.println("Mean: " + mean
                + "\nStandard Deviation: " + sDeviation);
        System.out.println("Question one was called and ran sucessfully.");
    }
}
