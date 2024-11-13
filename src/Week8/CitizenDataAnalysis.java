package Week8;

import java.util.*;
import java.io.*;

class Citizen {
    String code;
    String dateOfBirth;
    String fatherCode;
    String motherCode;
    char isAlive;
    String regionCode;

    public Citizen(String code, String dateOfBirth, String fatherCode, String motherCode, char isAlive, String regionCode) {
        this.code = code;
        this.dateOfBirth = dateOfBirth;
        this.fatherCode = fatherCode;
        this.motherCode = motherCode;
        this.isAlive = isAlive;
        this.regionCode = regionCode;
    }
}

public class CitizenDataAnalysis {
    private List<Citizen> citizens = new ArrayList<>();
    private Map<String, Integer> birthDateCount = new HashMap<>();
    private Map<String, List<String>> parentChildMap = new HashMap<>();

    public void loadData(BufferedReader reader) throws IOException {
        String line;
        while (!(line = reader.readLine()).equals("*")) {
            String[] data = line.split(" ");
            Citizen citizen = new Citizen(data[0], data[1], data[2], data[3], data[4].charAt(0), data[5]);
            citizens.add(citizen);
            birthDateCount.put(data[1], birthDateCount.getOrDefault(data[1], 0) + 1);
            if (!data[2].equals("0000000")) {
                parentChildMap.computeIfAbsent(data[2], k -> new ArrayList<>()).add(data[0]);
            }
            if (!data[3].equals("0000000")) {
                parentChildMap.computeIfAbsent(data[3], k -> new ArrayList<>()).add(data[0]);
            }
        }
    }

    public int numberOfPeople() {
        return citizens.size();
    }

    public int numberOfPeopleBornAt(String date) {
        return birthDateCount.getOrDefault(date, 0);
    }

    public int mostAliveAncestor(String code) {
        Set<String> visited = new HashSet<>();
        return findMostAliveAncestor(code, visited, 0);
    }

    private int findMostAliveAncestor(String code, Set<String> visited, int depth) {
        for (Citizen citizen : citizens) {
            if (citizen.code.equals(code) && citizen.isAlive == 'Y') {
                // Recur for ancestors
                visited.add(code);
                int maxDepth = depth;
                if (!citizen.fatherCode.equals("0000000") && !visited.contains(citizen.fatherCode)) {
                    maxDepth = Math.max(maxDepth, findMostAliveAncestor(citizen.fatherCode, visited, depth + 1));
                }
                if (!citizen.motherCode.equals("0000000") && !visited.contains(citizen.motherCode)) {
                    maxDepth = Math.max(maxDepth, findMostAliveAncestor(citizen.motherCode, visited, depth + 1));
                }
                return maxDepth;
            }
        }
        return -1; // Not found
    }

    public int numberOfPeopleBornBetween(String fromDate, String toDate) {
        int count = 0;
        for (Citizen citizen : citizens) {
            if (isBetween(citizen.dateOfBirth, fromDate, toDate)) {
                count++;
            }
        }
        return count;
    }

    private boolean isBetween(String date, String fromDate, String toDate) {
        return (date.compareTo(fromDate) >= 0) && (date.compareTo(toDate) <= 0);
    }

    public void executeQueries(BufferedReader reader) throws IOException {
        String line;
        while (!(line = reader.readLine()).equals("***")) {
            String[] queryParts = line.split(" ");
            switch (queryParts[0]) {
                case "NUMBER_PEOPLE":
                    System.out.println(numberOfPeople());
                    break;
                case "NUMBER_PEOPLE_BORN_AT":
                    System.out.println(numberOfPeopleBornAt(queryParts[1]));
                    break;
                case "MOST_ALIVE_ANCESTOR":
                    System.out.println(mostAliveAncestor(queryParts[1]));
                    break;
                case "NUMBER_PEOPLE_BORN_BETWEEN":
                    System.out.println(numberOfPeopleBornBetween(queryParts[1], queryParts[2]));
                    break;
                // Implement MAX_UNRELATED_PEOPLE as per requirement
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CitizenDataAnalysis analysis = new CitizenDataAnalysis();
        analysis.loadData(reader);
        analysis.executeQueries(reader);
    }
}
