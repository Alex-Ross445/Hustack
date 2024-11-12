package Week3;


//Week 3 - Linked List Manipulation
//        Description
//        Viết chương trình thực hiện công việc sau:
//        Xây dựng danh sách liên kết với các khóa được cung cấp ban đầu là dãy a
//        1
//        , a
//        2
//        , …, a
//        n
//        , sau đó thực hiện các thao tác trên danh sách bao gồm: thêm 1 phần tử vào đầu, vào cuối danh sách, hoặc vào trước, vào sau 1 phần tử nào đó trong danh sách, hoặc loại bỏ 1 phần tử nào đó trong danh sách
//
//        Input
//        Dòng 1: ghi số nguyên dương n (1 <= n <= 1000)
//        Dòng 2: ghi các số nguyên dương a
//        1
//        , a
//        2
//        , …, a
//        n
//        .
//        Các dòng tiếp theo lần lượt là các lệnh để thao tác (kết thúc bởi ký hiệu #) với các loại sau:
//        addlast  k: thêm phần tử có key bằng k vào cuối danh sách (nếu k chưa tồn tại)
//        addfirst  k: thêm phần tử có key bằng k vào đầu danh sách (nếu k chưa tồn tại)
//        addafter  u  v: thêm phần tử có key bằng u vào sau phần tử có key bằng v trên danh sách (nếu v đã tồn tại trên danh sách và u chưa tồn tại)
//        addbefore  u  v: thêm phần tử có key bằng  u vào trước phần tử có key bằng v trên danh sách (nếu v đã tồn tại trên danh sách và u của tồn tại)
//        remove  k: loại bỏ phần tử có key bằng k khỏi danh sách
//        reverse: đảo ngược thứ tự các phần tử của danh sách (không được cấp phát mới các phần tử, chỉ được thay đổi mối nối liên kết)
//        Output
//        Ghi ra dãy khóa của danh sách thu được sau 1 chuỗi các lệnh thao tác đã cho
//
//        Example
//        Input
//        5
//        5 4 3 2 1
//        addlast 3
//        addlast 10
//        addfirst 1
//        addafter 10 4
//        remove 1
//        #
//
//        Output
//        5 4 3 2 10


import java.util.Scanner;

class ListNode {
    int key;
    ListNode next;

    public ListNode(int key) {
        this.key = key;
        this.next = null;
    }
}

class LinkedList {
    private ListNode head;

    // Constructor
    public LinkedList() {
        head = null;
    }

    // Add a node at the end
    public void addLast(int key) {
        if (contains(key)) return; // Do not add if the key already exists
        ListNode newNode = new ListNode(key);
        if (head == null) {
            head = newNode;
            return;
        }
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Add a node at the beginning
    public void addFirst(int key) {
        if (contains(key)) return; // Do not add if the key already exists
        ListNode newNode = new ListNode(key);
        newNode.next = head;
        head = newNode;
    }

    // Add a node after a specific node
    public void addAfter(int u, int v) {
        if (contains(u) || !contains(v)) return; // Only add if v exists and u does not
        ListNode current = head;
        while (current != null) {
            if (current.key == v) {
                ListNode newNode = new ListNode(u);
                newNode.next = current.next;
                current.next = newNode;
                return;
            }
            current = current.next;
        }
    }

    // Add a node before a specific node
    public void addBefore(int u, int v) {
        if (contains(u) || !contains(v)) return; // Only add if v exists and u does not
        ListNode newNode = new ListNode(u);
        if (head == null) return;

        if (head.key == v) {
            newNode.next = head;
            head = newNode;
            return;
        }

        ListNode current = head;
        while (current.next != null) {
            if (current.next.key == v) {
                newNode.next = current.next;
                current.next = newNode;
                return;
            }
            current = current.next;
        }
    }

    // Remove a node with a specific key
    public void remove(int key) {
        if (head == null) return;

        if (head.key == key) {
            head = head.next;
            return;
        }

        ListNode current = head;
        while (current.next != null) {
            if (current.next.key == key) {
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
    }

    // Reverse the linked list
    public void reverse() {
        ListNode prev = null;
        ListNode current = head;
        ListNode next;
        while (current != null) {
            next = current.next; // Store next
            current.next = prev; // Reverse the current node's pointer
            prev = current; // Move pointers one position forward
            current = next;
        }
        head = prev; // Update head to new front of the list
    }

    // Check if the key exists in the list
    private boolean contains(int key) {
        ListNode current = head;
        while (current != null) {
            if (current.key == key) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Print the linked list
    public void printList() {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.key + " ");
            current = current.next;
        }
    }
}

public class LinkedListManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        LinkedList linkedList = new LinkedList();

        // Initialize the linked list with the given keys
        for (int i = 0; i < n; i++) {
            int key = scanner.nextInt();
            linkedList.addLast(key);
        }
        scanner.nextLine(); // Consume the newline

        // Process commands
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("#")) break; // Terminate on #

            String[] parts = command.split(" ");
            switch (parts[0]) {
                case "addlast":
                    linkedList.addLast(Integer.parseInt(parts[1]));
                    break;
                case "addfirst":
                    linkedList.addFirst(Integer.parseInt(parts[1]));
                    break;
                case "addafter":
                    linkedList.addAfter(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                case "addbefore":
                    linkedList.addBefore(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                case "remove":
                    linkedList.remove(Integer.parseInt(parts[1]));
                    break;
                case "reverse":
                    linkedList.reverse();
                    break;
            }
        }

        // Print the final state of the linked list
        linkedList.printList();
    }
}