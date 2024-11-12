package Week3;


//Week 3 - Tree manipulation & Traversal
//        Description
//        Mỗi nút trên cây có trường id (identifier) là một số nguyên (id của các nút trên cây đôi một khác nhau)
//        Thực hiện 1 chuỗi các hành động sau đây bao gồm các thao tác liên quan đến xây dựng cây và duyệt cây
//        · MakeRoot u: Tạo ra nút gốc u của cây
//        · Insert u v: tạo mới 1 nút u và chèn vào cuối danh sách nút con của nút v (nếu nút có id bằng v không tồn tại hoặc nút có id bằng u đã tồn tại thì không chèn thêm mới)
//        · PreOrder: in ra thứ tự các nút trong phép duyệt cây theo thứ tự trước
//        · InOrder: in ra thứ tự các nút trong phép duyệt cây theo thứ tự giữa
//        · PostOrder: in ra thứ tự các nút trong phép duyệt cây theo thứ tự sau
//        Dữ liệu: bao gồm các dòng, mỗi dòng là 1 trong số các hành động được mô tả ở trên, dòng cuối dùng là * (đánh dấu sự kết thúc của dữ liệu).
//        Kết quả: ghi ra trên mỗi dòng, thứ tự các nút được thăm trong phép duyệt theo thứ tự trước, giữa, sau của các hành động PreOrder, InOrder, PostOrder tương ứng đọc được từ dữ liệu đầu vào
//        Ví dụ
//        Dữ liệu
//        MakeRoot 10
//        Insert 11 10
//        Insert 1 10
//        Insert 3 10
//        InOrder
//        Insert 5 11
//        Insert 4 11
//        Insert 8 3
//        PreOrder
//        Insert 2 3
//        Insert 7 3
//        Insert 6 4
//        Insert 9 4
//        InOrder
//        PostOrder
//        *
//        Kết quả
//        11 10 1 3
//        10 11 5 4 1 3 8
//        5 11 6 4 9 10 1 8 3 2 7
//        5 6 9 4 11 1 8 2 7 3 10


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TreeNode {
    int id;
    List<TreeNode> children;

    public TreeNode(int id) {
        this.id = id;
        this.children = new ArrayList<>();
    }
}

class Tree {
    private TreeNode root;

    // Create the root of the tree
    public void makeRoot(int id) {
        if (root == null) {
            root = new TreeNode(id);
        }
    }

    // Insert a new node with id `u` into the children of node with id `v`
    public void insert(int u, int v) {
        if (findNode(root, u) != null || findNode(root, v) == null) {
            return; // Do not insert if node u already exists or node v doesn't exist
        }
        TreeNode parentNode = findNode(root, v);
        if (parentNode != null) {
            parentNode.children.add(new TreeNode(u)); // Add new child
        }
    }

    // Pre-order traversal
    public void preOrder() {
        List<Integer> result = new ArrayList<>();
        preOrderTraversal(root, result);
        printList(result);
    }

    // In-order traversal
    public void inOrder() {
        List<Integer> result = new ArrayList<>();
        inOrderTraversal(root, result);
        printList(result);
    }

    // Post-order traversal
    public void postOrder() {
        List<Integer> result = new ArrayList<>();
        postOrderTraversal(root, result);
        printList(result);
    }

    private TreeNode findNode(TreeNode node, int id) {
        if (node == null) return null;
        if (node.id == id) return node;
        for (TreeNode child : node.children) {
            TreeNode found = findNode(child, id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    private void preOrderTraversal(TreeNode node, List<Integer> result) {
        if (node == null) return;
        result.add(node.id);
        for (TreeNode child : node.children) {
            preOrderTraversal(child, result);
        }
    }

    private void inOrderTraversal(TreeNode node, List<Integer> result) {
        if (node == null) return;
        int childCount = node.children.size();
        for (int i = 0; i < childCount; i++) {
            if (i == childCount / 2) result.add(node.id); // Add the node in the middle
            inOrderTraversal(node.children.get(i), result);
        }
        if (childCount == 0) { // If there are no children, add the node
            result.add(node.id);
        }
    }

    private void postOrderTraversal(TreeNode node, List<Integer> result) {
        if (node == null) return;
        for (TreeNode child : node.children) {
            postOrderTraversal(child, result);
        }
        result.add(node.id);
    }

    private void printList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) System.out.print(" ");
        }
        System.out.println();
    }
}

public class TreeManipulationTraversal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tree tree = new Tree();

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("*")) break; // Terminate on *

            String[] parts = command.split(" ");
            switch (parts[0]) {
                case "MakeRoot":
                    int rootId = Integer.parseInt(parts[1]);
                    tree.makeRoot(rootId);
                    break;
                case "Insert":
                    int u = Integer.parseInt(parts[1]);
                    int v = Integer.parseInt(parts[2]);
                    tree.insert(u, v);
                    break;
                case "PreOrder":
                    tree.preOrder();
                    break;
                case "InOrder":
                    tree.inOrder();
                    break;
                case "PostOrder":
                    tree.postOrder();
                    break;
            }
        }
        scanner.close();
    }
}