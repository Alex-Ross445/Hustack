package Week1;


//Week 1 - Convert a TEXT to Upper-Case
//        Description
//        Given a TEXT, write a program that converts the TEXT to upper-case.
//
//        Input
//        The TEXT
//
//        Output
//        The TEXT in which characters are converted into upper-case
//
//        Example
//        Input
//        Hello John,
//        How are you?
//
//        Bye,
//
//        Output
//        HELLO JOHN,
//        HOW ARE YOU?
//
//        BYE,


import java.util.Scanner;

public class TextToUpperCase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder text = new StringBuilder();

        // Read the input text
        while (scanner.hasNextLine()) {
            text.append(scanner.nextLine()).append("\n");
        }

        // Convert to uppercase and print the result
        System.out.print(text.toString().toUpperCase());

        scanner.close();
    }
}