package day04;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Part2 {

    private static final List<String> LEGAL_CHARACTERS = List.of("MAS", "SAM");

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

        counter = solve(mat.toArray(new char[mat.size()][]));

        System.out.printf("Solution part2: %d\n", counter);
    }

    private static int solve(char[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int count = 0;

        for (int i = 0; i < row; i ++) {
            for (int j = 0; j < col; j++) {
                char c = mat[i][j];
                if (checkXMas(mat, i, j, c))
                    count++;
            }
        }
        return count;
    }

    private static boolean checkXMas(char[][] mat, int rowC, int colC, char c) {
        int row = mat.length;
        int col = mat[0].length;

        if ( rowC-1 < 0 || colC-1 < 0 || rowC + 1 >= row || colC + 1 >= col) return false;

        String str1 =
                new String(
                        new char[]{
                                mat[rowC-1][colC-1],
                                c,
                                mat[rowC+1][colC+1]
                        }
                );

        String str2 =
                new String(
                        new char[]{
                                mat[rowC-1][colC+1],
                                c,
                                mat[rowC+1][colC-1]
                        }
                );


        return LEGAL_CHARACTERS.contains(str1) && LEGAL_CHARACTERS.contains(str2);
    }

}
