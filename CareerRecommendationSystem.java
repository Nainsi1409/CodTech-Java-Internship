package com.codtech.recommendation;

import java.util.*;

public class CareerRecommendationSystem {

    public static void main(String[] args) {

        // Career data with required skills
        Map<String, List<String>> careers = new HashMap<>();

        careers.put("Software Developer", Arrays.asList("Java", "DSA", "Problem Solving"));
        careers.put("Web Developer", Arrays.asList("HTML", "CSS", "JavaScript"));
        careers.put("Data Analyst", Arrays.asList("Python", "SQL", "Statistics"));
        careers.put("AI Engineer", Arrays.asList("Python", "Machine Learning", "Data Science"));
        careers.put("Cyber Security", Arrays.asList("Networking", "Security", "Linux"));

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your skills (comma separated): ");
        String input = sc.nextLine();

        List<String> userSkills = new ArrayList<>();
        for (String skill : input.split(",")) {
            userSkills.add(skill.trim());
        }

        Map<String, Integer> scores = new HashMap<>();

        // Calculate similarity score
        for (String career : careers.keySet()) {
            List<String> requiredSkills = careers.get(career);
            int score = 0;

            for (String skill : userSkills) {
                if (requiredSkills.contains(skill)) {
                    score++;
                }
            }

            if (score > 0) {
                scores.put(career, score);
            }
        }

        // Sort by score
        List<Map.Entry<String, Integer>> sortedList =
                new ArrayList<>(scores.entrySet());

        sortedList.sort((a, b) -> b.getValue() - a.getValue());

        // Display results
        System.out.println("\nTop Career Recommendations:");
        for (Map.Entry<String, Integer> entry : sortedList) {
            System.out.println(entry.getKey() + " (Match Score: " + entry.getValue() + ")");
        }

        if (sortedList.isEmpty()) {
            System.out.println("No matching career found. Try adding more skills.");
        }
    }
}