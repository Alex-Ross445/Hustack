package Week1;


//Week 1 - Sum Array
//        Description
//        Given a sequence of integers a1, a2, ..., an. Compute the sum Q of elements of this sequence.
//        Input
//        Line 1: contains n (1 <= n <= 1000000)
//        Line 2: contains a1, a2, ..., an (-10000 <= ai <= 10000)
//        Output
//        Write the value of Q
//        Example
//        Input
//        4
//        3 2 5 4
//        Output
//        14


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArrayCounting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read the number of integers
        int n = Integer.parseInt(br.readLine().trim());

        // Read the integers and compute the sum
        String[] numbers = br.readLine().trim().split(" ");
        long sum = 0; // Use long to avoid overflow

        for (int i = 0; i < n; i++) {
            sum += Integer.parseInt(numbers[i]);
        }

        // Output the sum
        System.out.println(sum);
    }
}