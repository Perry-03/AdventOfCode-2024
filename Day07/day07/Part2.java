package day07;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiFunction;

public class Part2 {

    public static void main(String[] args) {
        File input = new File("Day07/day07/input.txt");
        long tot = 0;
        try (Scanner sc = new Scanner(input)) {
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(":");
                long res = Long.parseLong(line[0]);
                LinkedList<Long> values = new LinkedList<>();

                try (Scanner sc2 = new Scanner(line[1])) {
                    while (sc2.hasNext()) values.add(sc2.nextLong());
                }

                if (isCorrect(res, values)) tot += res;
            }

        } catch (Exception e) { e.printStackTrace(); }
        System.out.printf("Solution part1: %d\n", tot);
    }

    private static boolean isCorrect(long res, LinkedList<Long> values) {
        BiFunction<Long, Long, Long>[] funs = new BiFunction[3];
        funs[0] = Long :: sum;
        funs[1] = (a, b) -> a * b;

        BiFunction<String, String, Long> concat = (str1, str2) -> Long.parseLong(str1.concat(str2));
        return aux(res, values, funs, concat, values.pop());
    }

    private static boolean aux(long res, LinkedList<Long> values, BiFunction<Long, Long, Long>[] funs,
                               BiFunction<String, String, Long> concat, long partial) {

        if (values.isEmpty()) return res == partial;

        long a = values.pop();

        if (aux(res, new LinkedList<>(values), funs, concat, funs[0].apply(partial, a))) return true;
        else if (aux(res, new LinkedList<>(values), funs, concat, funs[1].apply(partial, a))) return true;
        return aux(res, new LinkedList<>(values), funs, concat, concat.apply(String.valueOf(partial), String.valueOf(a)));
    }
}
