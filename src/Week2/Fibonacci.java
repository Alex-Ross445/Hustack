package Week2;


//Week 2 - Dãy fibonacci
//        Description
//        Given a fibonacci sequence a[0], a[1], a[2], ... in which:  a[0] = 0, a[1] = 1, a[n] = a[n-1] + a[n-2], for all n >= 2
//        Given  positive integer n, compute a[n-1].
//        Input
//        Line 1: contains a positive integer n (2 <= n <= 21)
//        Output
//        Write a[n-1]
//        Example
//        Input
//        9
//        Output
//        21


import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the positive integer n
        int n = scanner.nextInt();

        /* Compute the (n-1)th fibonacci number */
        int result = fibonacci(n - 1);

        // Output the result
        System.out.println(result);

        scanner.close();
    }

    /* Method to compute the fibonacci number using iteration */
    private static int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int a = 0; // a[0]
        int b = 1; // a[1]
        int fib = 0;

        for (int i = 2; i <= n; i++) {
            fib = a + b; // a[n] = a[n-1] + a[n-2]
            a = b; // move forward
            b = fib; // move forward
        }

        return fib; // return a[n]
    }
}