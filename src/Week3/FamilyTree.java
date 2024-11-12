package Week3;


//Week 3 - Family Tree
//        Description
//        Given a family tree represented by child-parent (c,p) relations in which c is a child of p. Perform queries about the family tree:
//        descendants <name>: return number of descendants of the given <name>
//        generation <name>: return the number of generations of the descendants of the given <name>
//
//        Note that: the total number of people in the family is less than or equal to 10
//        4
//        Input
//        Contains two blocks. The first block contains information about child-parent, including lines (terminated by a line containing ***), each line contains: <child> <parent> where <child> is a string represented the name of the child and <parent> is a string represented the name of the parent. The second block contains lines (terminated by a line containing ***), each line contains two string <cmd> and <param> where <cmd> is the command (which can be descendants or generation) and <param> is the given name of the person participating in the  query.
//        Output
//        Each line is the result of a corresponding query.
//        Example
//        Input
//        Peter Newman
//        Michael Thomas
//        John David
//        Paul Mark
//        Stephan Mark
//        Pierre Thomas
//        Mark Newman
//        Bill David
//        David Newman
//        Thomas Mark
//        ***
//        descendants Newman
//        descendants Mark
//        descendants David
//        generation Mark
//        ***
//        Output
//        10
//        5
//        2
//        2


import java.util.*;

public class FamilyTree {
    private Map<String, List<String>> tree;

    public FamilyTree() {
        tree = new HashMap<>();
    }

    // Adds a child-parent relationship to the tree
    public void addRelation(String child, String parent) {
        tree.putIfAbsent(parent, new ArrayList<>());
        tree.get(parent).add(child);
    }

    // Returns the number of descendants for the given name
    public int countDescendants(String name) {
        return countDescendantsHelper(name) - 1; // Subtract 1 to exclude the person themselves
    }

    private int countDescendantsHelper(String name) {
        if (!tree.containsKey(name)) {
            return 1; // Count the person themselves if they have no children
        }
        int count = 1; // Count this person
        for (String child : tree.get(name)) {
            count += countDescendantsHelper(child); // Count all descendants
        }
        return count;
    }

    // Returns the number of generations of the descendants for the given name
    public int countGenerations(String name) {
        return countGenerationsHelper(name);
    }

    private int countGenerationsHelper(String name) {
        if (!tree.containsKey(name)) {
            return 0; // No descendants means 0 generations
        }
        int maxGenerations = 0;
        for (String child : tree.get(name)) {
            maxGenerations = Math.max(maxGenerations, countGenerationsHelper(child) + 1);
        }
        return maxGenerations;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FamilyTree familyTree = new FamilyTree();

        // Read child-parent relationships
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("***")) break; // Stop reading when seeing ***
            String[] parts = line.split(" ");
            String child = parts[0];
            String parent = parts[1];
            familyTree.addRelation(child, parent);
        }

        // Read commands
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("***")) break; // Stop reading when seeing ***
            String[] parts = line.split(" ");
            String command = parts[0];
            String param = parts[1];

            if (command.equals("descendants")) {
                System.out.println(familyTree.countDescendants(param));
            } else if (command.equals("generation")) {
                System.out.println(familyTree.countGenerations(param));
            }
        }
    }
}