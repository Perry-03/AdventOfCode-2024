package day08;

import java.io.File;
import java.util.*;

public class Part2 {
    private record Point(int x, int y, char c) {}
    private static Set<Point> antinodes = new HashSet<>();
    private static List<Point> antennas = new ArrayList<>();

    public static void main(String[] args) {
        File input = new File("Day08/day08/input.txt");
        int row = 0;
        int col = 0;
        // Leggi il file e raggruppa le antenne per frequenza
        try (Scanner sc = new Scanner(input)) {
            while (sc.hasNextLine()) {
                int tmp = 0;
                String line = sc.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    if (ch != '.') {
                        antennas.add(new Point(row, i, ch));
                    }
                    tmp++;
                }
                col = tmp;
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int counter = 0;

        for (Point a : antennas) {
            for (Point b : antennas) {
                if (a != b) calculateAntinodes(a, b, row, col);
            }
        }
        System.out.printf("Solution part2: %d\n", antinodes.size());
    }

    private static void calculateAntinodes(Point a, Point b, int row, int col) {
        if (a.c != b.c) return;
        List<Point> points = new ArrayList<>();
        int distanceX = Math.abs(a.x - b.x);
        int distanceY = Math.abs(a.y - b.y);

        Point antinode = new Point(a.x, a.y, a.c);
        do {
            if (antinode.x > b.x) {
                if (antinode.y > b.y) antinode = new Point(antinode.x + distanceX, antinode.y + distanceY, '#');
                else if (antinode.y < b.y) antinode = new Point(antinode.x + distanceX, antinode.y - distanceY, '#');
                else antinode = new Point(antinode.x + distanceX, antinode.y, '#');

            } else if (antinode.x < b.x) {
                if (antinode.y > b.y) antinode = new Point(antinode.x - distanceX, antinode.y + distanceY, '#');
                else if (antinode.y < b.y) antinode = new Point(antinode.x - distanceX, antinode.y - distanceY, '#');
                else antinode = new Point(antinode.x - distanceX, antinode.y, '#');

            } else {
                if (antinode.y < b.y) antinode = new Point(antinode.x, antinode.y - distanceY, '#');
                else antinode = new Point(antinode.x, antinode.y + distanceY, '#');
            }

            if (isIn(antinode, row, col)) {
                points.add(antinode);
            }

        } while (isIn(antinode, row, col));
        antinodes.addAll(points);
    }

    private static boolean isIn(Point p, int row, int col) {
        return p.x >= 0 && p.y >= 0 && p.x <= row && p.y <= col;
    }
}
