package day02;

import java.io.File;
import java.util.*;
import java.util.function.BiPredicate;

public class Part1 {
    public static void main(String[] args) {
        int countSafe = 0;
        File input = new File("Day02/day02/input.txt");

        BiPredicate<Integer, Integer> allAsc = (s1, s2) -> s1.compareTo(s2) < 0;
        BiPredicate<Integer, Integer> allDec = (s1, s2) -> s1.compareTo(s2) > 0;
        BiPredicate<Integer, Integer> allRng = (s1, s2) -> Math.abs(s1 - s2) <= 3;

        try (Scanner sc = new Scanner(input)) {
            while (sc.hasNextLine()) {
                int[] levels =
                        Arrays.stream(sc.nextLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();

                boolean isOk = true;
                BiPredicate<Integer, Integer> choosen = (allAsc.test(levels[0], levels[1]) ? allAsc : allDec).and(allRng);

                for (int i = 0; i < levels.length - 1; i++) isOk &= choosen.test(levels[i], levels[i+1]);

                if (isOk) countSafe++;
            }
        } catch (Exception e) {
            System.out.printf("%s\n", e.getMessage());
        }

        System.out.printf("Solution part1: %d\n", countSafe);
    }
}
