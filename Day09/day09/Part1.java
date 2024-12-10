package day09;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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
        } catch (Exception e) { e.printStackTrace(); }

        String[] amphipodSplitted = amphipod.toString().split(" ");
        System.out.println((amphipod));
        Pattern pattern = Pattern.compile("\\d+");

        for (int i = amphipod.length() - 1; i >= 0; i--) {
            if (!isOk(amphipod)) {
                amphipod.setCharAt(amphipod.indexOf("."), amphipod.charAt(i));
                amphipod.setCharAt(i, '.');
            }
        }

        long result = 0;

        int finalIndex = amphipod.indexOf(".");

        for (int i = 0; i < finalIndex; i++) {
            int token = Integer.parseInt(amphipod.charAt(i) + "");
            result += (long) token * i;
        }



        System.out.printf("Solution part1: %s\n", amphipod);
    }

    private static boolean isOk(StringBuilder amphipod) {
        int indexOfDot = amphipod.indexOf(".");

        for (int i = indexOfDot + 1; i < amphipod.length(); i++)
            if (amphipod.charAt(i) != '.') return false;
        return true;
    }
}
