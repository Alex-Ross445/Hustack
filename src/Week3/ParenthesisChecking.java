package Week3;


//Week 3 - Check Parenthesis
//        Description
//        Given a string containing only characters (, ), [, ] {, }. Write a program that checks whether the string is correct in expression.
//        Example
//        ([]{()}()[]): correct
//        ([]{()]()[]): incorrect
//        Input
//        One line contains the string (the length of the string is less than or equal to $10^6$)One line contains the string (the length of the string is less than or equal to 10
//        6
//        )
//        Output
//        Write 1 if the sequence is correct, and write 0, otherwise
//        Example
//        Input
//        (()[][]{}){}{}[][]({[]()})
//        Output
//        1


import java.util.Scanner;
import java.util.Stack;

public class ParenthesisChecking {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the string containing brackets: ");
        String input = scanner.nextLine();

        // Check if the string is balanced
        int result = isBalanced(input) ? 1 : 0;
        System.out.println(result);
    }

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch); // Push opening brackets onto the stack
            } else if (ch == ')' || ch == ']' || ch == '}') {
                // If we encounter a closing bracket, check for corresponding opening bracket
                if (stack.isEmpty()) {
                    return false; // No matching opening bracket
                }

                char top = stack.pop(); // Pop the top of the stack
                if (!isMatchingPair(top, ch)) {
                    return false; // Mismatched brackets
                }
            }
        }

        return stack.isEmpty(); // If stack is empty, all brackets are matched
    }

    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
                (opening == '[' && closing == ']') ||
                (opening == '{' && closing == '}');
    }
}