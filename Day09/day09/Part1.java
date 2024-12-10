package day09;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Part1 {

    public static void main(String[] args) {
        int id = 0;
        StringBuilder amphipod = new StringBuilder();

        File input = new File("Day09/day09/input.txt");

        try (Scanner sc = new Scanner(input)) {
            boolean isBlock = true;
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
            for (int i = 0; i < sb.length(); i++) {
                int token = Integer.parseInt(sb.charAt(i) + "");
                if (isBlock) {
                    amphipod.append((id + " ").repeat(token));
                    id++;
                    isBlock = false;
                } else {
                    amphipod.append(". ".repeat(token));
                    isBlock = true;
                }
            }

            String[] splitted = amphipod.toString().split(" ");
            for (int j = splitted.length-1; j >= 0 && !isOk(splitted); j--) {
                String v = splitted[j];
                if (!v.equals(".")) {
                    int index = getIndex(splitted);

                    splitted[index] = v;
                    splitted[j] = ".";
                }
            }
            long result = 0;

            int finalIndex = getIndex(splitted);

            for (int i = 0; i < finalIndex; i++) {
                int token = Integer.parseInt(splitted[i]);
                result += (long) token * i;
            }
            System.out.printf("Solution part1: %d\n", result);

        } catch (Exception e) { e.printStackTrace(); }


    }

    private static int getIndex(String[] splitted) {
        return IntStream.range(0, splitted.length)
                .filter(x -> ".".equals(splitted[x]))
                .findFirst()
                .orElse(-1);
    }

    private static boolean isOk(String[] splitted) {
        String str = String.join("", splitted);
        int indexOfDot = str.indexOf(".");

        for (int i = indexOfDot + 1; i < str.length(); i++)
            if (str.charAt(i) != '.') return false;
        return true;
    }
    
    
}
