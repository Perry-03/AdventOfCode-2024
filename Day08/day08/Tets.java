package day08;

import java.util.List;

public class Tets {
    public static void main(String[] args) {
        // Griglia iniziale
        char[][] grid = {
                "............".toCharArray(),
                "........0...".toCharArray(),
                ".....0......".toCharArray(),
                ".......0....".toCharArray(),
                "....0.......".toCharArray(),
                "......A.....".toCharArray(),
                "............".toCharArray(),
                "............".toCharArray(),
                "........A...".toCharArray(),
                ".........A..".toCharArray(),
                "............".toCharArray(),
                "............".toCharArray()
        };

        // Lista delle coordinate da sostituire con '#'
        List<Point> antinodes = List.of(
                new Point(1, 3, '#'),
                new Point(0, 1, '#'),
                new Point(7, 7, '#'),
                new Point(5, 5, '#'),
                new Point(3, 3, '#'),
                new Point(1, 1, '#'),
                new Point(11, 11, '#'),
                new Point(7, 5, '#'),
                new Point(6, 3, '#'),
                new Point(5, 1, '#'),
                new Point(11, 3, '#'),
                new Point(10, 1, '#'),
                new Point(2, 10, '#'),
                new Point(0, 6, '#'),
                new Point(2, 4, '#'),
                new Point(5, 6, '#'),
                new Point(6, 6, '#'),
                new Point(4, 4, '#'),
                new Point(2, 2, '#'),
                new Point(0, 0, '#'),
                new Point(10, 10, '#'),
                new Point(12, 12, '#'),
                new Point(3, 2, '#'),
                new Point(11, 10, '#'),
                new Point(9, 4, '#'),
                new Point(8, 2, '#'),
                new Point(7, 0, '#'),
                new Point(12, 0, '#'),
                new Point(0, 11, '#'),
                new Point(5, 11, '#'),
                new Point(4, 9, '#')
        );

        // Sostituisci i caratteri nella griglia
        for (Point p : antinodes) {
            if (p.x >= 0 && p.x < grid.length && p.y >= 0 && p.y < grid[0].length) {
                grid[p.x][p.y] = p.c;
            }
        }

        // Stampa la griglia modificata
        for (char[] row : grid) {
            System.out.println(new String(row));
        }
    }

    private static class Point {
        int x, y;
        char c;

        Point(int x, int y, char c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
}
