package Week8;

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

    public User(String userId) {
        this.userId = userId;
    }

    public void addSubmission(Submission submission) {
        if (submission.status.equals("ERR")) {
            errorCount++;
        }
        problemPoints.put(submission.problemId, Math.max(problemPoints.getOrDefault(submission.problemId, 0), submission.points));
    }

    public void calculateTotalPoints() {
        for (int points : problemPoints.values()) {
            totalPoints += points;
        }
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
                user.calculateTotalPoints();
                System.out.println(user.totalPoints);
            } else if (query.startsWith("?number_submission_period")) {
                String[] times = query.split(" ");
                String fromTime = times[1];
                String toTime = times[2];
                int count = (int) submissions.stream()
                        .filter(s -> s.timePoint.compareTo(fromTime) >= 0 && s.timePoint.compareTo(toTime) <= 0)
                        .count();
                System.out.println(count);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContestSubmissionAnalyzer analyzer = new ContestSubmissionAnalyzer();

        // Read submissions
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("#")) {
                break;
            }
            analyzer.processSubmission(line);
        }

        // Read queries
        List<String> queries = new ArrayList<>();
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("#")) {
                break;
            }
            queries.add(line);
        }

        analyzer.processQueries(queries);
        scanner.close();
    }
}