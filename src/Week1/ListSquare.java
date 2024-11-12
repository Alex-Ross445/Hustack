package Week1;


//Week 1 - List all numbers from 1 to n and its squares
//        Description
//        Given an integer n, print numbers from 1 to n and its squares.
//        Input
//        Line 1: contains a positive integer n (1 <= n <= 100)
//        Output
//        Each line i (i = 1,...,n): contains i and i^2 (elements are separated by one SPACE character)
//
//        Example
//        Input
//        3
//        Output
//        1 1
//        2 4
//        3 9


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListSquare {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read the positive integer n
        int n = Integer.parseInt(br.readLine().trim());

        // Loop from 1 to n and print i and i^2
        for (int i = 1; i <= n; i++) {
            System.out.println(i + " " + (i * i));
        }
    }
}