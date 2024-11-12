package Week4;


//Description
//        Cho dãy a1, a2, ..., an trong đó các phần tử đôi một khác nhau và 1 giá trị nguyên dương M. Hãy đếm số Q các cặp (i,j) sao cho 1 <= i < j <= n và ai + aj = M.
//
//        Dữ liệu
//        Dòng 1: ghi n và M (1 <= n, M <= 1000000)
//        Dòng 2: ghi a1, a2, ..., an
//        Kết quả
//        Ghi ra giá trị Q
//        Ví dụ
//        Dữ liệu
//        5 6
//        5 2 1 4 3
//        Kết quả
//        2


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class SumPairSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Read n and M
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);

        // Read the array
        String[] secondLine = reader.readLine().split(" ");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(secondLine[i]);
        }

        // HashSet to keep track of elements seen so far
        HashSet<Integer> seen = new HashSet<>();
        int count = 0;

        // Iterate through the array
        for (int i = 0; i < n; i++) {
            int complement = M - a[i];
            // Check if the complement exists in the seen set
            if (seen.contains(complement)) {
                count++;  // Found a valid pair
            }
            // Add current element to the seen set
            seen.add(a[i]);
        }

        // Output the result
        System.out.println(count);

        reader.close();
    }
}