package Week1;


//Week 1 - List sequence of integer having 3 digits divisible by n
//        Description
//        Given a positive integer n, find all integer having 3 digits which is divisible by n.
//        Input
//        Line 1: contains a positive integer n (1 <= n <= 999)
//        Output
//        Write the sequence of numbers found (elements are separated by a SPACE character)
//
//        Example
//        Input
//        200
//
//        Output
//        200 400 600 800


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreeDigitsDivisibles {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read the positive integer n
        int n = Integer.parseInt(br.readLine().trim());

        // StringBuilder to collect results
        StringBuilder result = new StringBuilder();

        // Iterate through three-digit numbers
        for (int i = 100; i <= 999; i++) {
            if (i % n == 0) {
                // Append the number to the result
                result.append(i).append(" ");
            }
        }

        // Print the result, trimming any trailing spaces
        System.out.println(result.toString().trim());
    }
}