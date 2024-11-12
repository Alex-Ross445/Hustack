package Week1;


//Week 1 - Add Subtract Multiplication Division of A and B
//        Description
//        Given 2 integers a and b. Compute a+b, a-b, a*b, a/b.
//        Input
//        Line 1 contains 2 integers a and b (1 <= a,b <= 1000)
//        Output
//        Write a+b, a-b, a*b, a/b  (4 integers are separated by a SPACE characters)
//
//        Example
//        Input
//        9 4
//
//        Output
//        13 5 36 2


import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read two integers from input
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        // Perform calculations
        int sum = a + b;
        int difference = a - b;
        int product = a * b;
        int quotient = a / b;

        // Print results
        System.out.println(sum + " " + difference + " " + product + " " + quotient);

        // Close the scanner
        scanner.close();
    }
}