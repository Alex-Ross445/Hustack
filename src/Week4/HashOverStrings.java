package Week4;


//Week 4 - Hash Over Strings
//        Description
//        Given a string s[1…k] which is a sequence of characters taken from {‘a’, . . ., ‘z’}. Given a positive integer m, the hash code of s is defined by the formula:
//        H(s) =  (s[1]*256
//        k-1
//        + s[2]*256
//        k-2
//        + . . . + s[k]*256
//        0
//        ) mod m  (the contant integer m is a parameter)
//        Given a sequence of strings k1, k2, …, kn, compute the corresponding hash codes
//        Input
//        Line 1: n and m (1 <= n,m <= 100000)
//        Line i+1 (i = 1,2,…,n): contains the string ki (the length of each string is less than or equal to 200)
//        Output
//        Each line contains the corresponding hash code of n given strings
//        Example
//        Input
//        4 1000
//        a
//        ab
//        abc
//        abcd
//        Output
//        97
//        930
//        179
//        924


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HashOverStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read n and m
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        // List to store the strings
        List<String> strings = new ArrayList<>();

        // Process each string and store it
        for (int i = 0; i < n; i++) {
            strings.add(scanner.nextLine());
        }

        scanner.close();

        // Print the hash codes
        for (String s : strings) {
            long hashCode = computeHash(s, m); // Calculate hash code
            System.out.println(hashCode);
        }
    }

    private static long computeHash(String s, int m) {
        long hash = 0; // Change to long
        int k = s.length();

        for (int i = 0; i < k; i++) {
            // Calculate each character's contribution to the hash
            hash = (hash + (s.charAt(i) * power(256, k - 1 - i, m))) % m;
        }

        return hash;
    }

    private static long power(int base, int exp, int mod) {
        long result = 1;
        long baseMod = base % mod; // Apply modulo to the base
        while (exp > 0) {
            if ((exp & 1) == 1) { // If exp is odd, multiply base with result
                result = (result * baseMod) % mod;
            }
            baseMod = (baseMod * baseMod) % mod; // Square the base
            exp >>= 1; // Divide exp by 2
        }
        return result;
    }
}