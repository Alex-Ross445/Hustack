package Week1;


//Week 1 - Count odd and even number from a sequence
//        Description
//        Given a sequence of integer a1, a2, ..., an. Count the number of odd elements and even elements of the sequence.
//        Input
//        Line 1: contains a positive integer n (1 <= n <= 100000)
//        Line 2: contains a1, a2, ..., an. (1 <= ai <= 1000000)
//        Output
//        Write the number of odd elements and the number of even elements (separated by a SPACE character)
//
//        Example
//        Input
//        6
//        2 3 4 3 7 1
//        Output
//        4 2


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class OddEvenCounter {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        // Example: Reading input
        int n = Integer.parseInt(br.readLine());

        // Your algorithm here
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += i; // Sample calculation
        }

        // Output result
        pw.println(result);
        pw.flush();
    }
}