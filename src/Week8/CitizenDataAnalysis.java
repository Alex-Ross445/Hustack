package Week8;

import java.util.*;
import java.text.SimpleDateFormat;

public class CitizenDataAnalysis {

    static class Citizen {
        String code;
        String dateOfBirth;
        String fatherCode;
        String motherCode;
        char isAlive;
        String regionCode;

        Citizen(String code, String dateOfBirth, String fatherCode, String motherCode, char isAlive, String regionCode) {
            this.code = code;
            this.dateOfBirth = dateOfBirth;
            this.fatherCode = fatherCode;
            this.motherCode = motherCode;
            this.isAlive = isAlive;
            this.regionCode = regionCode;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read citizen data
        List<Citizen> citizens = new ArrayList<>();
        Map<String, Integer> birthCounts = new HashMap<>();

        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equals("*")) {
                break;
            }
            String[] parts = line.split(" ");
            Citizen citizen = new Citizen(parts[0], parts[1], parts[2], parts[3], parts[4].charAt(0), parts[5]);
            citizens.add(citizen);
            birthCounts.put(parts[1], birthCounts.getOrDefault(parts[1], 0) + 1);
        }

        // Read queries
        List<String> queries = new ArrayList<>();
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equals("***")) {
                break;
            }
            queries.add(line);
        }

        // Process queries
        for (String query : queries) {
            String[] parts = query.split(" ");
            String command = parts[0];

            switch (command) {
                case "NUMBER_PEOPLE":
                    System.out.println(citizens.size());
                    break;
                case "NUMBER_PEOPLE_BORN_AT":
                    System.out.println(birthCounts.getOrDefault(parts[1], 0));
                    break;
                case "MOST_ALIVE_ANCESTOR":
                    System.out.println(mostAliveAncestor(citizens, parts[1]));
                    break;
                case "NUMBER_PEOPLE_BORN_BETWEEN":
                    System.out.println(numberPeopleBornBetween(birthCounts, parts[1], parts[2]));
                    break;
                case "MAX_UNRELATED_PEOPLE":
                    System.out.println(maxUnrelatedPeople(citizens));
                    break;
            }
        }

        scanner.close();
    }

    private static int mostAliveAncestor(List<Citizen> citizens, String code) {
        Set<String> visited = new HashSet<>();
        String currentCode = code;
        int distance = 0;

        while (currentCode != null && !currentCode.equals("0000000")) {
            if (visited.contains(currentCode)) {
                return -1; // Cycle detected
            }
            visited.add(currentCode);

            Citizen person = findCitizen(citizens, currentCode);
            if (person == null || person.isAlive == 'N') {
                break;
            }

            currentCode = person.fatherCode; // Move to father
            distance++;
        }

        return distance - 1 >= 0 ? distance - 1 : 0;
    }

    private static Citizen findCitizen(List<Citizen> citizens, String code) {
        for (Citizen citizen : citizens) {
            if (citizen.code.equals(code)) {
                return citizen;
            }
        }
        return null;
    }

    private static int numberPeopleBornBetween(Map<String, Integer> birthCounts, String fromDate, String toDate) {
        int count = 0;
        for (String date : birthCounts.keySet()) {
            if (date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0) {
                count += birthCounts.get(date);
            }
        }
        return count;
    }

    private static int maxUnrelatedPeople(List<Citizen> citizens) {
        Set<String> unrelated = new HashSet<>();
        Set<String> related = new HashSet<>();

        for (Citizen citizen : citizens) {
            unrelated.add(citizen.code);
            if (!citizen.fatherCode.equals("0000000")) {
                related.add(citizen.fatherCode);
                related.add(citizen.code);
            }
            if (!citizen.motherCode.equals("0000000")) {
                related.add(citizen.motherCode);
                related.add(citizen.code);
            }
        }

        unrelated.removeAll(related);
        return unrelated.size();
    }
}