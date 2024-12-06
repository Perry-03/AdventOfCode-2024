package day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static final Map<String, Set<String>> rules = new HashMap<>();

    public static void main(String[] args) {
        int middlePages = 0;
        Path path = Paths.get("Day05/day05/input.txt");
        Pattern regex = Pattern.compile("\\d+\\|\\d+|\\d+(,\\d+)*");

        try {
            String content = Files.readString(path);
            Matcher matcher = regex.matcher(content);

            while (matcher.find()) {
                String group = matcher.group(0);
                String[] splitted = group.contains("|") ? group.split("\\|") : group.split(",");

                if (splitted.length == 2)
                    rules.computeIfAbsent(splitted[1], k -> new HashSet<>()).add(splitted[0]);
                else middlePages += updateInRightOrder(splitted);


            }

        } catch (IOException e) { System.out.printf("%s\n", e.getMessage()); }
        System.out.printf("Solution part1: %d\n", middlePages);
    }

    private static int updateInRightOrder(String[] updates) {
        for (int i = 0; i < updates.length; i++)
            for (int j = i+1; j < updates.length; j++) {
                if (!check(updates[i], updates[j])) return 0;
            }

        return Integer.parseInt(updates[(updates.length / 2)]);
    }

    private static boolean check(String page1, String page2) {
        if (!rules.containsKey(page1)) return true;
        else return !rules.get(page1).contains(page2);
    }


}
