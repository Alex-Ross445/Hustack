package Week3;


//Week 3 - WATER JUGS
//        Description
//        There are two jugs, a-litres jug and b-litres jug (a, b are positive integers). There is a pump with unlimited water. Given a positive integer c, how to get exactly c litres.
//        Input
//        Line 1: contains positive integers a,   b,  c  (1 <= a, b, c <= 900)
//        Output
//        write the number of steps or write -1 (if no solution found)
//        Example
//
//        Input
//        6  8  4
//        Output
//        4


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

class State {
    int a; // amount in jug a
    int b; // amount in jug b
    int steps; // number of steps taken

    public State(int a, int b, int steps) {
        this.a = a;
        this.b = b;
        this.steps = steps;
    }
}

public class WaterJugs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        scanner.close();

        int result = getMinSteps(a, b, c);
        System.out.println(result);
    }

    public static int getMinSteps(int a, int b, int c) {
        if (c > Math.max(a, b)) {
            return -1; // Impossible to measure more than the largest jug
        }

        // To keep track of visited states
        Set<String> visited = new HashSet<>();
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(0, 0, 0)); // start with both jugs empty

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // Check if we have achieved the goal
            if (current.a == c || current.b == c) {
                return current.steps;
            }

            // Create a unique state identifier
            String stateId = current.a + "," + current.b;
            if (visited.contains(stateId)) {
                continue; // Skip if we've already visited this state
            }
            visited.add(stateId);

            // Generate all possible next states
            // Fill jug a
            queue.add(new State(a, current.b, current.steps + 1));
            // Fill jug b
            queue.add(new State(current.a, b, current.steps + 1));
            // Empty jug a
            queue.add(new State(0, current.b, current.steps + 1));
            // Empty jug b
            queue.add(new State(current.a, 0, current.steps + 1));
            // Pour from a to b
            int pourToB = Math.min(current.a, b - current.b);
            queue.add(new State(current.a - pourToB, current.b + pourToB, current.steps + 1));
            // Pour from b to a
            int pourToA = Math.min(current.b, a - current.a);
            queue.add(new State(current.a + pourToA, current.b - pourToA, current.steps + 1));
        }

        return -1; // If no solution is found
    }
}