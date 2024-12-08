package day06;

import java.io.File;
import java.util.*;
import java.util.function.Function;

public class Part1 {
    record Pair(int x, int y) {
        public Pair sum(final Pair direction) {
            return new Pair(x + direction.x(), y + direction.y());
        }
    }

    private static Map<String, Function<Pair, Pair>> redirect = new HashMap<>();

    public static void main(String[] args) {
        Pair guardPosition = new Pair(0, 0);
        Set<Pair> seen = new HashSet<>();
        int maxCols = 0;
        int maxRows = 0;


        List<Pair> obstacles = new ArrayList<>();

        File input = new File("Day06/day06/input.txt");

        initMap();

        try (Scanner sc = new Scanner(input)) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                int mapCols = 0;
                   for (int i = 0; i < line.length(); i++) {

                        char next = line.charAt(i);
                        if (next == '#') obstacles.add(new Pair(maxRows, mapCols));
                        else if (next == '^') {
                            guardPosition = new Pair(maxRows, mapCols);
                            seen.add(guardPosition);
                        }

                        mapCols++;
                        maxCols++;
                    }


                maxRows++;
            }

        } catch (Exception e) { e.printStackTrace(); }

        String direction = "up";

        while (!isOut(guardPosition, maxRows, maxCols)) {
            Pair futurePos = redirect.get(direction).apply(guardPosition);
            if (obstacles.contains(futurePos)) direction = changeDir(direction);
            else {
                guardPosition = redirect.get(direction).apply(guardPosition);
                seen.add(guardPosition);
            }
        }
        System.out.printf("Solution part1: %d\n", seen.size()-1);
    }

    private static void initMap() {
        redirect.put("up",    (x) -> x.sum(new Pair(-1, 0)));

        redirect.put("down",  (x) -> x.sum(new Pair(1, 0)));

        redirect.put("right", (x) -> x.sum(new Pair(0, 1)));

        redirect.put("left",  (x) -> x.sum(new Pair(0, -1)));
    }

    private static boolean isOut(Pair guard, int mapRows, int maxCols) {
        return guard.x < 0 || guard.x >= mapRows || guard.y < 0 || guard.y >= maxCols;
    }

    private static String changeDir(final String dir) {
        switch (dir) {
            case "up"    -> { return "right"; }
            case "down"  -> { return "left"; }
            case "left"  -> { return "up"; }
            case "right" -> { return "down"; }
        }
        return "up";
    }
}
