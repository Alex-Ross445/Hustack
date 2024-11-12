package Week4;


//Week 4 - Kiểm tra xuất hiện
//        Description
//        Cho dãy số nguyên A1, A2, . . . , An với mỗi số nguyên Ai kiểm tra xem có số Aj nào bằng Ai hay không với j<i.
//        Input
//        Dòng đầu chứa số n (1≤n≤100,000)
//        Dòng hai chứa nn số nguyên A1, A2, ..., An (1≤Ai≤1000,000,000)
//        Output
//        Ghi ra n dòng, dòng thứ i in ra 1 nếu tồn tại Aj=Ai với j<i, ngược lại in ra 0.
//        Example
//        input
//        5
//        1 4 3 1 4
//        output
//        0
//        0
//        0
//        1
//        1


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class DuplicateChecker {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            // Read the number of elements
            int n = Integer.parseInt(reader.readLine());
            String line = reader.readLine();
            String[] elements = line.split(" ");

            // HashSet to keep track of seen numbers
            HashSet<Integer> seenNumbers = new HashSet<>();

            // Iterate through the array and check for duplicates
            for (int i = 0; i < n; i++) {
                int number = Integer.parseInt(elements[i]);
                if (seenNumbers.contains(number)) {
                    System.out.println(1); // Found a duplicate
                } else {
                    System.out.println(0); // No duplicate found
                    seenNumbers.add(number); // Add the number to the set
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}