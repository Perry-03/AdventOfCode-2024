package day04;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part1 {

    private static final String LEGAL_CHARACTERS = "XMAS";
    private static final int[][] DIRECTIONS = {
            {0, 1},     // Destra
            {0, -1},    // Sinistra
            {1, 0},     // Giù
            {-1, 0},    // Su
            {1, 1},     // Diagonale in basso a destra
            {1, -1},    // Diagonale in basso a sinistra
            {-1, 1},    // Diagonale in alto a destra
            {-1, -1}    // Diagonale in alto a sinistra
    };

    public static void main(String[] args) {

        int counter = 0;

        File input = new File("Day04/day04/input.txt");

        List<char[]> mat = new ArrayList<>();

        try (Scanner sc = new Scanner(input)) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                mat.add(line.toCharArray());
            }

        } catch (Exception e) { e.printStackTrace(); }

        char[][] charMat = mat.toArray(new char[mat.size()][]);

        counter = solve(charMat);

        System.out.printf("Solution part1: %d\n", counter);
    }

    private static int solve(char[][] mat) {
        int rows = mat.length;
        int col = mat[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                char literal = mat[i][j];
                for (int[] d : DIRECTIONS)
                    if (existsWord(mat, i, j, literal, d[0], d[1]))
                        count++;
            }
        }
        return count;
    }

    private static boolean existsWord(char[][] mat, int startRow, int startCol, char literal, int dirRow, int dirCol) {
        int rows = mat.length;
        int cols = mat[0].length;
        int len = LEGAL_CHARACTERS.length();

        for (int i = 0; i < len; i++) {
            int newRow = startRow + i * dirRow;
            int newCol = startCol + i * dirCol;

            if (newRow < 0 || newRow >= rows || newCol < 0 ||
                    newCol >= cols || mat[newRow][newCol] != LEGAL_CHARACTERS.charAt(i)) {
                return false;
            }
        }
        return true;
    }


}
