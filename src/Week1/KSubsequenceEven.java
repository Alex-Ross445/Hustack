package Week1;


//Week 1 - k-Subsequence even
//        Description
//        Given a sequence of integers a1, a2, . . ., an. A k-subsequence is define to be a sequence of k consecutive elements: ai, ai+1, . . ., ai+k-1. The weight of a k-subsequence is the sum of its elements.
//        Given positive integers k and m. Compute the number Q of k-subsequences such that the weight is even.
//        Input
//        Line 1: contains 2 positive integers n, k (1 <= n <= 100000, 1 <= k <= n/2)
//        Line 2: contains a1, a2, . . ., an. (1 <= ai <= 10000)
//        Output
//        Write the value Q
//        Example
//        Input
//        6  3
//        2 4 5 1 1 2
//        Output
//        2


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KSubsequenceEven {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read n and k
        String[] input = br.readLine().trim().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        // Read the sequence of integers
        String[] numbers = br.readLine().trim().split(" ");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(numbers[i]);
        }

        // Initialize the sum of the first k elements
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += a[i];
        }

        // Count the number of k-subsequences with even weight
        int count = 0;
        if (sum % 2 == 0) {
            count++;
        }

        // Sliding window to check the rest of the subsequences
        for (int i = k; i < n; i++) {
            sum += a[i] - a[i - k]; // Update the sum for the new k-subsequence
            if (sum % 2 == 0) {
                count++;
            }
        }

        // Output the result
        System.out.println(count);
    }
}