package Week1;


//Week 1 - Count words
//        Description
//        Given a Text, write a prorgam to count the number Q of words (ignore characters SPACE, TAB, LineBreak) of this Text
//
//        Input
//        The Text
//
//        Output
//        Write the number Q of words
//
//        Example
//        Input
//        Hanoi University Of Science and Technology
//        School of Information and Communication Technology
//
//
//        Output
//        12


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WordCounting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder text = new StringBuilder();
        String line;

        // Read multiple lines of input until EOF
        while ((line = br.readLine()) != null) {
            text.append(line).append(" ");  // Append line with a space
        }

        // Split the text into words, using whitespace as the delimiter
        String[] words = text.toString().trim().split("\\s+");

        // Count the number of words
        int wordCount = words.length;

        // Output the number of words
        System.out.println(wordCount);
    }
}