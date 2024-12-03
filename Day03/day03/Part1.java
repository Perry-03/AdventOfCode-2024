package day03;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) {
        int result = 0;
        Path path = Paths.get("Day03/day03/input.txt");

        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";

        Pattern pattern = Pattern.compile(regex);
        try {
            String content = Files.readString(path);
            Matcher matcher = pattern.matcher(content);

            while (matcher.find()) {
                int fst = Integer.parseInt(matcher.group(1));
                int snd = Integer.parseInt(matcher.group(2));

                result += fst * snd;
            }
        } catch (Exception e) { System.out.printf("%s\n", e.getMessage()); }

        System.out.printf("Solution part1: %d\n", result);
    }
}
