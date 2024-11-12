package Week7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Order {
    String customerID;
    String productID;
    int price;
    String shopID;
    String timePoint;

    public Order(String customerID, String productID, int price, String shopID, String timePoint) {
        this.customerID = customerID;
        this.productID = productID;
        this.price = price;
        this.shopID = shopID;
        this.timePoint = timePoint;
    }
}

public class SalesOrderAnalyzer {
    private List<Order> orders = new ArrayList<>();
    private Map<String, Integer> revenueByShop = new HashMap<>();
    private Map<String, Map<String, Integer>> revenueByCustomerAndShop = new HashMap<>();
    private int totalRevenue = 0;

    public void addOrder(Order order) {
        orders.add(order);
        totalRevenue += order.price;

        // Update revenue for the shop
        revenueByShop.put(order.shopID, revenueByShop.getOrDefault(order.shopID, 0) + order.price);

        // Update revenue for the customer in that shop
        revenueByCustomerAndShop
                .computeIfAbsent(order.customerID, k -> new HashMap<>())
                .put(order.shopID, revenueByCustomerAndShop.get(order.customerID).getOrDefault(order.shopID, 0) + order.price);
    }

    public int getTotalNumberOrders() {
        return orders.size();
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public int getRevenueOfShop(String shopID) {
        return revenueByShop.getOrDefault(shopID, 0);
    }

    public int getTotalConsumeOfCustomerShop(String customerID, String shopID) {
        return revenueByCustomerAndShop.getOrDefault(customerID, Collections.emptyMap()).getOrDefault(shopID, 0);
    }

    public int getTotalRevenueInPeriod(String fromTime, String toTime) {
        int revenueInPeriod = 0;
        for (Order order : orders) {
            if (isWithinPeriod(order.timePoint, fromTime, toTime)) {
                revenueInPeriod += order.price;
            }
        }
        return revenueInPeriod;
    }

    private boolean isWithinPeriod(String orderTime, String fromTime, String toTime) {
        return orderTime.compareTo(fromTime) >= 0 && orderTime.compareTo(toTime) <= 0;
    }

    public static void main(String[] args) throws IOException {
        SalesOrderAnalyzer analyzer = new SalesOrderAnalyzer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        // Read orders
        String line;
        while (!(line = reader.readLine()).equals("#")) {
            String[] parts = line.split(" ");
            String customerID = parts[0];
            String productID = parts[1];
            int price = Integer.parseInt(parts[2]);
            String shopID = parts[3];
            String timePoint = parts[4];
            analyzer.addOrder(new Order(customerID, productID, price, shopID, timePoint));
        }

        // Read queries
        while (!(line = reader.readLine()).equals("#")) {
            if (line.equals("?total_number_orders")) {
                writer.write(String.valueOf(analyzer.getTotalNumberOrders()));
                writer.newLine();
            } else if (line.equals("?total_revenue")) {
                writer.write(String.valueOf(analyzer.getTotalRevenue()));
                writer.newLine();
            } else if (line.startsWith("?revenue_of_shop")) {
                String shopID = line.split(" ")[1];
                writer.write(String.valueOf(analyzer.getRevenueOfShop(shopID)));
                writer.newLine();
            } else if (line.startsWith("?total_consume_of_customer_shop")) {
                String[] parts = line.split(" ");
                String customerID = parts[1];
                String shopID = parts[2];
                writer.write(String.valueOf(analyzer.getTotalConsumeOfCustomerShop(customerID, shopID)));
                writer.newLine();
            } else if (line.startsWith("?total_revenue_in_period")) {
                String[] parts = line.split(" ");
                String fromTime = parts[1];
                String toTime = parts[2];
                writer.write(String.valueOf(analyzer.getTotalRevenueInPeriod(fromTime, toTime)));
                writer.newLine();
            }
        }

        // Flush and close the writer
        writer.flush();
        writer.close();
        reader.close();
    }
}