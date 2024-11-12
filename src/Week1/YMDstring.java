package Week1;


//Week 1 - Extract Year, Month, Date from a String YYYY-MM-DD
//        Description
//        Given a date which is a string under the format YYYY-MM-DD (in which YYYY is the year, MM is the month (the month í from 1 to 12), and DD is the date (the date is from 1 to 31)). Extract the year, month and date.
//        Input
//        Line 1: contains a string s
//        Output
//        if s is not under the format YYYY-MM-DD, then write INCORRECT. Otherwise, write year, month, and date separated by a SPACE character
//
//        Example
//        Input
//        2023-10-04
//        Output
//        2023 10 4
//
//
//        Input
//        2023-10-4
//        Output
//        INCORRECT
//
//        Input
//        2023-10 04
//        Output
//        INCORRECT


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class YMDstring {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String dateString = br.readLine().trim();

        // Regular expression to match the format YYYY-MM-DD
        String regex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";

        if (dateString.matches(regex)) {
            // Split the date string
            String[] parts = dateString.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);

            // Print the result
            System.out.println(year + " " + month + " " + day);
        } else {
            System.out.println("INCORRECT");
        }
    }
}