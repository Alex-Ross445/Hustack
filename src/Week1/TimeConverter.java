package Week1;


//Week 1 - Convert hh:mm:ss to seconds
//        Description
//        Given a time moment which is a string under the format hh:mm:ss (in which hh (0 <= hh <= 23) is the hour, mm (0 <= mm <= 59) is the minute, and ss (0 <= ss <= 59) is the second). Convert this time moment in seconds (result = hh*3600 + mm*60 + ss).
//        Input
//        Line 1: contains a string s representing the time moment.
//        Output
//        if s is not under the format hh:mm:ss, then write INCORRECT. Otherwise, write value converted.
//
//        Example
//        Input
//        13:05:26
//
//        Output
//        47126
//
//
//        Input
//        13:05:6
//
//        Output
//        INCORRECT
//
//        Input
//        13:05 26
//
//        Output
//        INCORRECT


import java.util.Scanner;
import java.util.regex.Pattern;

public class TimeConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Regular expression to match the format hh:mm:ss
        String regex = "^([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$";
        Pattern pattern = Pattern.compile(regex);

        // Check if the input matches the regex
        if (pattern.matcher(input).matches()) {
            // Split the input string by ":"
            String[] timeParts = input.split(":");
            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);
            int seconds = Integer.parseInt(timeParts[2]);

            // Convert to total seconds
            int totalSeconds = hours * 3600 + minutes * 60 + seconds;
            System.out.println(totalSeconds);
        } else {
            System.out.println("INCORRECT");
        }

        scanner.close();
    }
}