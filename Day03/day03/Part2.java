package day03;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static void main(String[] args) {
        int result = 0;
        boolean enable = true;
        Path path = Paths.get("Day03/day03/input.txt");

        String regex = "(do|don't)\\(\\)|mul\\((\\d{1,3}),(\\d{1,3})\\)";

        Pattern pattern = Pattern.compile(regex);

        try {
            String content = Files.readString(path);

            Matcher matcher = pattern.matcher(content);

            while (matcher.find()) {
                if (matcher.group(1) != null) enable = matcher.group(1).endsWith("o");

                if (matcher.group(2) != null && matcher.group(3) != null) {
                    int fst = Integer.parseInt(matcher.group(2));
                    int snd = Integer.parseInt(matcher.group(3));

                    result += enable ? fst * snd : 0;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }

        System.out.printf("Solution part2: %d\n", result);
    }
}
