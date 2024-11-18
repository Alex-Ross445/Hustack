package Week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Submission {
    String userId;
    String problemId;
    String timePoint;
    String status;
    int points;

    public Submission(String userId, String problemId, String timePoint, String status, int points) {
        this.userId = userId;
        this.problemId = problemId;
        this.timePoint = timePoint;
        this.status = status;
        this.points = points;
    }
}

class User {
    String userId;
    int totalPoints = 0;
    int errorCount = 0;
    Map<String, Integer> problemPoints = new HashMap<>();
    boolean totalPointsCalculated = false;

    public User(String userId) {
        this.userId = userId;
    }

    public void addSubmission(Submission submission) {
        if (submission.status.equals("ERR")) {
            errorCount++;
        }
        problemPoints.put(submission.problemId, Math.max(problemPoints.getOrDefault(submission.problemId, 0), submission.points));
        totalPointsCalculated = false;
    }

    public int getTotalPoints() {
        if (!totalPointsCalculated) {
            totalPoints = problemPoints.values().stream().mapToInt(Integer::intValue).sum();
            totalPointsCalculated = true;
        }
        return totalPoints;
    }
}

public class ContestSubmissionAnalyzer {
    private List<Submission> submissions = new ArrayList<>();
    private Map<String, User> users = new HashMap<>();
    private int totalSubmissions = 0;
    private int totalErrors = 0;

    public void processSubmission(String line) {
        String[] parts = line.split(" ");
        String userId = parts[0];
        String problemId = parts[1];
        String timePoint = parts[2];
        String status = parts[3];
        int points = Integer.parseInt(parts[4]);

        Submission submission = new Submission(userId, problemId, timePoint, status, points);
        submissions.add(submission);
        totalSubmissions++;

        if (status.equals("ERR")) {
            totalErrors++;
        }

        users.putIfAbsent(userId, new User(userId));
        users.get(userId).addSubmission(submission);
    }

    public void processQueries(List<String> queries) {
        // Sort submissions by timePoint for efficient range queries
        submissions.sort(Comparator.comparing(s -> s.timePoint));

        for (String query : queries) {
            if (query.equals("?total_number_submissions")) {
                System.out.println(totalSubmissions);
            } else if (query.equals("?number_error_submision")) {
                System.out.println(totalErrors);
            } else if (query.startsWith("?number_error_submision_of_user")) {
                String userId = query.split(" ")[1];
                System.out.println(users.getOrDefault(userId, new User(userId)).errorCount);
            } else if (query.startsWith("?total_point_of_user")) {
                String userId = query.split(" ")[1];
                User user = users.getOrDefault(userId, new User(userId));
                System.out.println(user.getTotalPoints());
            } else if (query.startsWith("?number_submission_period")) {
                String[] times = query.split(" ");
                String fromTime = times[1];
                String toTime = times[2];

                // Use binary search to find the range
                int startIndex = findFirstSubmissionIndex(fromTime);
                int endIndex = findLastSubmissionIndex(toTime);

                // Calculate the count based on indices
                long count = Math.max(0, endIndex - startIndex + 1);
                System.out.println(count);
            }
        }
    }

    private int findFirstSubmissionIndex(String time) {
        int left = 0, right = submissions.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (submissions.get(mid).timePoint.compareTo(time) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left; // This is the first submission >= time
    }

    private int findLastSubmissionIndex(String time) {
        int left = 0, right = submissions.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (submissions.get(mid).timePoint.compareTo(time) <= 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right; // This is the last submission <= time
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ContestSubmissionAnalyzer analyzer = new ContestSubmissionAnalyzer();

        try {
            String line;
            while (!(line = reader.readLine()).equals("#")) {
                analyzer.processSubmission(line);
            }

            List<String> queries = new ArrayList<>();
            while (!(line = reader.readLine()).equals("#")) {
                queries.add(line);
            }

            analyzer.processQueries(queries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}