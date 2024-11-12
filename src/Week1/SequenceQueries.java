package Week1;


//Week 1 - Basic queries on array
//        Description
//        Given a sequence of integers a1, a2, ..., an. Perform a sequence of queries over this sequence including:
//        find-max: return the maximum element of the given sequence
//        find-min: return the minimum element of the given sequence
//        sum: return the sum of the elements of the given sequence
//        find-max-segment i j: return the maximum element of the subsequence from index i to index j (i <= j)
//
//        Input
//        The first block contains the information about the given sequence with the following format:
//        Line 1: contains a positive integer n (1 <= n <= 10000)
//        Line 2: contains n integers a1, a2, ..., an (-1000 <= ai <= 1000)
//        The first block is terminated by a character *
//        The second block contains a sequence of queries defined above, each query is in a line. The second block is terminated a 3 characters ***
//
//        Output
//        Write the result of each query in a corresponding line
//
//        Example
//        Input
//        5
//        1 4 3 2 5
//        *
//        find-max
//        find-min
//        find-max-segment 1 3
//        find-max-segment 2 5
//        sum
//        ***
//
//        Output
//        5
//        1
//        4
//        5
//        15


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SequenceQueries {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read the size of the sequence
        int n = Integer.parseInt(br.readLine().trim());
        int[] a = new int[n];

        // Read the sequence of integers
        String[] inputNumbers = br.readLine().trim().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(inputNumbers[i]);
        }

        // Read the termination character
        br.readLine(); // Read the '*'

        // Process queries
        String query;
        while (!(query = br.readLine().trim()).equals("***")) {
            switch (query) {
                case "find-max":
                    System.out.println(findMax(a));
                    break;
                case "find-min":
                    System.out.println(findMin(a));
                    break;
                case "sum":
                    System.out.println(sum(a));
                    break;
                default:
                    if (query.startsWith("find-max-segment")) {
                        String[] parts = query.split(" ");
                        int i = Integer.parseInt(parts[1]) - 1; // Convert to 0-based index
                        int j = Integer.parseInt(parts[2]) - 1; // Convert to 0-based index
                        System.out.println(findMaxSegment(a, i, j));
                    }
                    break;
            }
        }
    }

    // Method to find the maximum element in the array
    private static int findMax(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int value : a) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    // Method to find the minimum element in the array
    private static int findMin(int[] a) {
        int min = Integer.MAX_VALUE;
        for (int value : a) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    // Method to calculate the sum of the elements in the array
    private static int sum(int[] a) {
        int total = 0;
        for (int value : a) {
            total += value;
        }
        return total;
    }

    // Method to find the maximum element in a specified segment of the array
    private static int findMaxSegment(int[] a, int i, int j) {
        int max = Integer.MIN_VALUE;
        for (int index = i; index <= j; index++) {
            if (a[index] > max) {
                max = a[index];
            }
        }
        return max;
    }
}