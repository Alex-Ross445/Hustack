package Week4;


//Description
//        A database contains a sequence of key k1, k2, ..., kn which are strings (1<=n<=100000). Perform a sequence of actions of two kinds:
//        · find k: find and return 1 if k exists in the database, and return 0, otherwise
//        · insert k: insert a key k into the database and return 1 if the insertion is successful (k does not exist in the database) and return 0 if the insertion is failed (k exists in the database)
//        Note that the length of any key is greater than 0 and less than or equal to 50.
//        Input
//        Two blocks of information. The first block contains a key of (k1,k2,...,kn) in each line. The first block is terminated with a line containing *. The second block is a sequence of actions of two finds described above: each line contains 2 string: cmd and k in which cmd = find or insert and k is the key (parameter of the action). The second block is terminated with a line containing ***. Note that the number of actions can be up to 100000.
//        Output
//        Each line contains the result (0 or 1) of the corresponding action.
//        Example
//        Input
//        computer
//        university
//        school
//        technology
//        phone
//        *
//        find school
//        find book
//        insert book
//        find algorithm
//        find book
//        insert book
//        ***
//        Output
//        1
//        0
//        1
//        0
//        1
//        0


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class StoreSearchString {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> database = new HashSet<>();

        // First block: Read keys until '*' is encountered
        String line;
        while (!(line = reader.readLine()).equals("*")) {
            database.add(line);
        }

        // Second block: Read commands until '***' is encountered
        while (!(line = reader.readLine()).equals("***")) {
            String[] parts = line.split(" ");
            String command = parts[0];
            String key = parts[1];

            if (command.equals("find")) {
                // Check if the key exists in the database
                if (database.contains(key)) {
                    System.out.println(1); // Key exists
                } else {
                    System.out.println(0); // Key does not exist
                }
            } else if (command.equals("insert")) {
                // Attempt to insert the key into the database
                boolean isInserted = database.add(key);
                System.out.println(isInserted ? 1 : 0); // 1 if successful, 0 if failed
            }
        }

        reader.close();
    }
}