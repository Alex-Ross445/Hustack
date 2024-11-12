package Week2;


//Week 2 - Binary sequences generation without consecutive 11
//        Description
//        Given an integer n, write a program that generates all binary sequences without consecutive 11 in a lexicographic order.
//        Input
//        Line 1: contains an integer n (1 <= n <= 20)
//        Output
//        Write binary sequences in a lexicographic order, each sequence in a line
//        Example
//        Input
//        3
//        Output
//        000
//        001
//        010
//        100
//        101


import java.util.Scanner;

public class BinarySequenceWithout11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the integer n from input
        int n = scanner.nextInt();
        generateBinarySequences(n, "", 0);

        scanner.close();
    }

    // Recursive method to generate binary sequences
    private static void generateBinarySequences(int n, String sequence, int lastDigit) {
        // Base case: if the sequence length is n, print it
        if (sequence.length() == n) {
            System.out.println(sequence);
            return;
        }

        // Append '0' to the sequence and recurse
        generateBinarySequences(n, sequence + "0", 0);

        // Append '1' to the sequence only if the last digit is not '1'
        if (lastDigit != 1) {
            generateBinarySequences(n, sequence + "1", 1);
        }
    }
}