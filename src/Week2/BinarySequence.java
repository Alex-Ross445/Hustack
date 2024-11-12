package Week2;


//Week 2 - Binary sequence generation
//        Description
//        Given an integer n, write a program that generates all the binary sequences of length n in a lexicographic order.
//        Input
//        Line 1: contains an integer n (1 <= n <= 20)
//        Output
//        Write binary sequences in a lexicographic ordder, eac sequence in a line
//
//        Example
//        Input
//        3
//        Output
//        000
//        001
//        010
//        011
//        100
//        101
//        110
//        111


import java.util.Scanner;

public class BinarySequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the integer n from input
        int n = scanner.nextInt();
        generateBinarySequences(n);

        scanner.close();
    }

    private static void generateBinarySequences(int n) {
        // The total number of binary sequences of length n is 2^n
        int totalSequences = (int) Math.pow(2, n);

        // Loop through all numbers from 0 to 2^n - 1
        for (int i = 0; i < totalSequences; i++) {
            // Create a StringBuilder to build the binary string
            StringBuilder binarySequence = new StringBuilder();

            // Generate binary representation of the number
            for (int j = n - 1; j >= 0; j--) {
                // Check if the j-th bit is set
                if ((i & (1 << j)) != 0) {
                    binarySequence.append("1");
                } else {
                    binarySequence.append("0");
                }
            }

            // Print the generated binary sequence
            System.out.println(binarySequence);
        }
    }
}