package Week1;


//Week 1 - Solve degree-2 polynomial equation
//        Description
//        Given an equation ax^2 + bx + c = 0. Find solution to the given equation.
//        Input
//        Line 1 contains 3 integers a, b, c
//        Output
//        Write NO SOLUTION if the given equation has no solution
//        Write x0 (2 digits after the decimal point) if the given equation has one solution x0
//        Write x1 and x2 with x1 < x2 (2 digits after the decimal point) if the given equation has two distinct solutions x1, x2
//
//        Example
//        Input
//        1 1 8
//        Output
//        NO SOLUTION


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class SecondDegreePolyEquation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().trim().split(" ");

        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        int c = Integer.parseInt(input[2]);

        // Check if a is zero (not a quadratic equation)
        if (a == 0) {
            System.out.println("NO SOLUTION");
            return;
        }

        // Calculate the discriminant
        double discriminant = b * b - 4 * a * c;

        // DecimalFormat to ensure leading zero
        DecimalFormat df = new DecimalFormat("0.00");

        if (discriminant < 0) {
            System.out.println("NO SOLUTION");
        } else if (discriminant == 0) {
            double x0 = -b / (2.0 * a);
            System.out.println(df.format(x0));
        } else {
            double sqrtD = Math.sqrt(discriminant);
            double x1 = (-b - sqrtD) / (2 * a);
            double x2 = (-b + sqrtD) / (2 * a);
            // Ensure x1 is the smaller one
            if (x1 > x2) {
                double temp = x1;
                x1 = x2;
                x2 = temp;
            }
            System.out.println(df.format(x1) + " " + df.format(x2));
        }
    }
}