package Week2;


//Week 2 - Permutation generation
//        Description
//        Given an integer n, write a program to generate all permutations of 1, 2, ..., n in a lexicalgraphic order (elements of a permutation are separated by a SPACE character).
//        Example
//        Input
//        3
//        Output
//        1 2 3
//        1 3 2
//        2 1 3
//        2 3 1
//        3 1 2
//        3 2 1


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Permutations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<List<Integer>> result = permute(n);

        // Use StringBuilder for efficient output
        StringBuilder output = new StringBuilder();
        for (List<Integer> permutation : result) {
            for (int num : permutation) {
                output.append(num).append(" ");
            }
            output.append("\n"); // Add a newline after each permutation
        }

        // Print all permutations at once
        System.out.print(output);
    }

    public static List<List<Integer>> permute(int n) {
        List<List<Integer>> permutations = new ArrayList<>();
        boolean[] used = new boolean[n + 1]; // To track used numbers
        List<Integer> current = new ArrayList<>();
        backtrack(permutations, current, used, n);
        return permutations;
    }

    private static void backtrack(List<List<Integer>> permutations, List<Integer> current, boolean[] used, int n) {
        if (current.size() == n) {
            permutations.add(new ArrayList<>(current));
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                used[i] = true; // Mark the number as used
                current.add(i); // Add number to the current permutation
                backtrack(permutations, current, used, n); // Recur
                current.remove(current.size() - 1); // Backtrack
                used[i] = false; // Mark the number as unused
            }
        }
    }
}