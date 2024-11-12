package Week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.*;

class Transaction {
    String fromAccount;
    String toAccount;
    int money;
    String timePoint;
    String atm;

    public Transaction(String fromAccount, String toAccount, int money, String timePoint, String atm) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.money = money;
        this.timePoint = timePoint;
        this.atm = atm;
    }
}

public class BankTransaction {
    private List<Transaction> transactions = new ArrayList<>();
    private Set<String> accountSet = new HashSet<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        accountSet.add(transaction.fromAccount);
        accountSet.add(transaction.toAccount);
    }

    public int numberOfTransactions() {
        return transactions.size();
    }

    public int totalMoneyTransaction() {
        return transactions.stream().mapToInt(t -> t.money).sum();
    }

    public List<String> listSortedAccounts() {
        return new ArrayList<>(accountSet).stream().sorted().collect(Collectors.toList());
    }

    public int totalMoneyTransactionFrom(String account) {
        return transactions.stream()
                .filter(t -> t.fromAccount.equals(account))
                .mapToInt(t -> t.money)
                .sum();
    }

    public int inspectCycle(String account, int k) {
        Set<String> visited = new HashSet<>();
        return hasCycle(account, k, visited, new ArrayList<>()) ? 1 : 0;
    }

    private boolean hasCycle(String currentAccount, int k, Set<String> visited, List<String> path) {
        if (k == 0) {
            return path.size() > 0 && path.get(0).equals(currentAccount);
        }

        if (visited.contains(currentAccount)) {
            return false;
        }

        visited.add(currentAccount);
        path.add(currentAccount);

        for (Transaction transaction : transactions) {
            if (transaction.fromAccount.equals(currentAccount)) {
                if (hasCycle(transaction.toAccount, k - 1, new HashSet<>(visited), path)) {
                    return true;
                }
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BankTransaction processor = new BankTransaction();

        // Read transaction data
        String line;
        while (!(line = reader.readLine()).equals("#")) {
            String[] parts = line.split(" ");
            String fromAccount = parts[0];
            String toAccount = parts[1];
            int money = Integer.parseInt(parts[2]);
            String timePoint = parts[3];
            String atm = parts[4];

            processor.addTransaction(new Transaction(fromAccount, toAccount, money, timePoint, atm));
        }

        // Read and process queries
        while (!(line = reader.readLine()).equals("#")) {
            if (line.equals("?number_transactions")) {
                System.out.println(processor.numberOfTransactions());
            } else if (line.equals("?total_money_transaction")) {
                System.out.println(processor.totalMoneyTransaction());
            } else if (line.equals("?list_sorted_accounts")) {
                System.out.println(String.join(" ", processor.listSortedAccounts()));
            } else if (line.startsWith("?total_money_transaction_from ")) {
                String account = line.split(" ")[1];
                System.out.println(processor.totalMoneyTransactionFrom(account));
            } else if (line.startsWith("?inspect_cycle ")) {
                String[] parts = line.split(" ");
                String account = parts[1];
                int k = Integer.parseInt(parts[2]);
                System.out.println(processor.inspectCycle(account, k));
            }
        }

        reader.close();
    }
}