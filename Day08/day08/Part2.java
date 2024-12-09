package day08;

import java.io.File;
import java.util.*;

public class Part2 {
    private record Point(int x, int y, char c) {}

    public static void main(String[] args) {
        File input = new File("Day08/day08/input.txt");
        Map<Character, List<Point>> antennas = new HashMap<>();
        Set<Point> antinodes = new HashSet<>();
        int cols = 0, row = 0;

        // Leggi il file e raggruppa le antenne per frequenza
        try (Scanner sc = new Scanner(input)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                cols = line.length();
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    if (ch != '.') {
                        antennas.putIfAbsent(ch, new ArrayList<>());
                        antennas.get(ch).add(new Point(row, i, ch));
                    }
                }
                row++;
            }
        } catch (Exception e) { e.printStackTrace(); }

        // Calcola gli antinodi
        for (var entry : antennas.entrySet()) {
            List<Point> freqAntennas = entry.getValue();

            // Aggiungi le antenne stesse agli antinodi se allineate
            for (Point a : freqAntennas) {
                for (Point b : freqAntennas) {
                    if (!a.equals(b) && isAligned(a, b)) {
                        antinodes.add(a);
                        antinodes.add(b);
                    }
                }
            }

            // Calcola tutti gli antinodi intermedi
            for (int i = 0; i < freqAntennas.size(); i++) {
                for (int j = i + 1; j < freqAntennas.size(); j++) {
                    Point a = freqAntennas.get(i);
                    Point b = freqAntennas.get(j);

                    if (isAligned(a, b)) {
                        Point midpoint = midpoint(a, b);
                        antinodes.add(midpoint);
                    }
                }
            }
        }

        System.out.printf("Solution part2: %d\n", antinodes.size());
    }

    // Verifica se due punti sono allineati
    private static boolean isAligned(Point a, Point b) {
        return a.x == b.x || a.y == b.y || Math.abs(a.x - b.x) == Math.abs(a.y - b.y);
    }

    // Calcola il punto intermedio tra due punti
    private static Point midpoint(Point a, Point b) {
        int midX = (a.x + b.x) / 2;
        int midY = (a.y + b.y) / 2;
        return new Point(midX, midY, '.');
    }
}
