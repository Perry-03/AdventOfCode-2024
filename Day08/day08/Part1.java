package day08;

import java.io.File;
import java.util.*;

public class Part1 {
    record Point(int x, int y, char c) {}

    public static void main(String[] args) {
        File input = new File("Day08/day08/input.txt");
        Set<Point> antennas = new HashSet<>();
        Set<Point> antinodes = new HashSet<>();
        int cols = 0;
        int row = 0;

        try (Scanner sc = new Scanner(input)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                cols = line.length();
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    if (ch != '.') antennas.add(new Point(row, i, ch));
                }
                row++;
            }
        } catch (Exception e) { System.out.printf("%s\n", e.getMessage()); }

        for (int i = 0; i < row; i++)
            for (int j = 0; j < cols; j++)
                if (isAntinode(new Point(i, j, '.'), antennas))
                    antinodes.add(new Point(i, j, '#'));

        System.out.printf("Solution part1: %d\n", antinodes.size());
    }

    private static boolean isAntinode(Point a, Set<Point> antennas) {
        for (Point freq : antennas)
            for (Point freq2 : antennas)
                if (freq != freq2 && collinearity(a, freq, freq2)) return true;

        return false;
    }

    private static boolean collinearity(final Point antinode, Point a, Point b) {
        if (a.c != b.c) return false; // Frequenze diverse, non valido

        int distanceXA = Math.abs(antinode.x - a.x);
        int distanceYA = Math.abs(antinode.y - a.y);

        int distanceXB = Math.abs(antinode.x - b.x);
        int distanceYB = Math.abs(antinode.y - b.y);

        if (!((distanceXB == 2 * distanceXA) || (distanceYB == 2 * distanceYA))) {
            return false;
        }

        return areAligned(antinode, a, b);
    }

    private static boolean areAligned(Point a, Point b, Point c) {
        int dx1 = b.x - a.x;
        int dy1 = b.y - a.y;
        int dx2 = c.x - a.x;
        int dy2 = c.y - a.y;

        return dx1 * dy2 == dy1 * dx2;
    }

}
