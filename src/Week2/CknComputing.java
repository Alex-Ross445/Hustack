package Week2;


//Week 2 - Compute C_k_n
//        Description
//        Given two positive integers k and n. Compute C(k,n) which is the number of ways to select k objects from a given set of n objects.
//        Input
//        Line 1: two positive integers k and n (1 <= k,n <= 999)
//        Output
//        Write te value C(k,n) modulo 10
//        9
//        +7.
//        Example
//        Input
//        3  5
//        Output
//        10


import java.util.Scanner;

public class CknComputing {
    static final int MOD = 1000000007;
    static long[] factorial;
    static long[] inverseFactorial;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read k and n
        int k = scanner.nextInt();
        int n = scanner.nextInt();

        // Precompute factorials and inverse factorials
        precomputeFactorials(n);

        // Calculate C(k, n) = n! / (k! * (n-k)!)
        long result = combination(n, k);

        // Output the result
        System.out.println(result);

        scanner.close();
    }

    private static void precomputeFactorials(int n) {
        factorial = new long[n + 1];
        inverseFactorial = new long[n + 1];

        // Compute all factorials % MOD
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i % MOD;
        }

        // Compute inverse factorials using Fermat's Little Theorem
        inverseFactorial[n] = modInverse(factorial[n]);
        for (int i = n - 1; i >= 0; i--) {
            inverseFactorial[i] = inverseFactorial[i + 1] * (i + 1) % MOD;
        }
    }

    private static long modInverse(long a) {
        return pow(a, MOD - 2);
    }

    private static long pow(long base, long exp) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % MOD;
            }
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }

    private static long combination(int n, int k) {
        if (k > n) {
            return 0;
        }
        return factorial[n] * inverseFactorial[k] % MOD * inverseFactorial[n - k] % MOD;
    }
}