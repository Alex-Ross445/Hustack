package Week3;


//Week 3 - Simulation Queue
//        Description
//        Perform a sequence of operations over a queue, each element is an integer:
//        PUSH v: push a value v into the queue
//        POP: remove an element out of the queue and print this element to stdout (print NULL if the queue is empty)
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
//        1
//        2
//        3
//
//        Input
//        PUSH 1
//        POP
//        POP
//        PUSH 4
//        POP
//        #
//        Output
//        1
//        NULL
//        4


import java.util.LinkedList;
import java.util.Scanner;

public class QueueSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Integer> queue = new LinkedList<>();
        StringBuilder output = new StringBuilder(); // To store the output of POP operations

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("#")) break; // Terminate on #

            String[] parts = command.split(" ");
            if (parts[0].equals("PUSH")) {
                int value = Integer.parseInt(parts[1]);
                queue.add(value); // Adds the value to the end of the queue
            } else if (parts[0].equals("POP")) {
                if (!queue.isEmpty()) {
                    output.append(queue.poll()).append("\n"); // Store the removed element
                } else {
                    output.append("NULL\n"); // Store NULL if the queue is empty
                }
            }
        }

        // Print all results of POP operations at once
        System.out.print(output.toString());
    }
}