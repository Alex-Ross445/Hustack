package Week3;


//Week 3 - Simulation Stack
//        Description
//        Perform a sequence of operations over a stack, each element is an integer:
//        PUSH v: push a value v into the stack
//        POP: remove an element out of the stack and print this element to stdout (print NULL if the stack is empty)
//        Input
//        Each line contains a command (operration) of type
//        PUSH  v
//        POP
//        Output
//        Write the results of POP operations (each result is written in a line)
//        Example
//        Input
//        PUSH 1
//        PUSH 2
//        PUSH 3
//        POP
//        POP
//        PUSH 4
//        PUSH 5
//        POP
//        #
//        Output
//        3
//        2
//        5


import java.util.Scanner;
import java.util.Stack;

public class StackSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>(); // Stack to store integers
        StringBuilder output = new StringBuilder(); // To store the output of POP operations

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("#")) break; // Terminate on #

            String[] parts = command.split(" ");
            if (parts[0].equals("PUSH")) {
                int value = Integer.parseInt(parts[1]);
                stack.push(value); // Push the value onto the stack
            } else if (parts[0].equals("POP")) {
                if (!stack.isEmpty()) {
                    output.append(stack.pop()).append("\n"); // Store the popped element
                } else {
                    output.append("NULL\n"); // Store NULL if the stack is empty
                }
            }
        }

        // Print all results of POP operations at once
        System.out.print(output.toString());
    }
}