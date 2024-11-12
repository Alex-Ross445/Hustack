package Week3;


//Week 3 - BST - Insertion and PreOrder Traversal
//        Description
//        Given a BST initialized by NULL. Perform a sequence of operations on a BST including:
//        insert k: insert a key k into the BST (do not insert if the key k exists)
//        Input
//        •Each line contains command under the form: “insert k”
//        •The input is terminated by a line containing #
//        Output
//        •Write the sequence of keys of nodes visited by the pre-order traversal (separated by a SPACE character)
//        Example
//        Input
//        insert 20
//        insert 10
//        insert 26
//        insert 7
//        insert 15
//        insert 23
//        insert 30
//        insert 3
//        insert 8
//        #
//        Output
//        20 10 7 3 8 15 26 23 30


import java.util.Scanner;

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    // Insert a key into the BST
    void insert(int key) {
        root = insertRec(root, key);
    }

    // Recursive function to insert a new key
    Node insertRec(Node root, int key) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(key);
            return root;
        }

        // Otherwise, recur down the tree
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        // If key is already present, do nothing

        return root;
    }

    // Pre-order traversal of the BST
    void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.key + " "); // Visit the root
        preOrder(node.left); // Traverse the left subtree
        preOrder(node.right); // Traverse the right subtree
    }
}

public class BSTInsert {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        while (true) {
            String input = scanner.nextLine();
            // Terminate on #
            if (input.equals("#")) {
                break;
            }
            // Process the insert command
            if (input.startsWith("insert ")) {
                String[] parts = input.split(" ");
                int key = Integer.parseInt(parts[1]);
                bst.insert(key);
            }
        }

        // Output the pre-order traversal
        bst.preOrder(bst.root);
    }
}