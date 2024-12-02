import java.io.File;
import java.util.*;

public class Part1 {
    public static void main(String[] args) {
        int totalDistance = 0;
        List<Integer> fst = new ArrayList<>();
        List<Integer> snd = new ArrayList<>();
        File input = new File("Day01/input.txt");

        try (Scanner sc = new Scanner(input)) {
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split("\\s+");
                fst.add(Integer.parseInt(line[0]));
                snd.add(Integer.parseInt(line[1]));
            }
        } catch (Exception e) {
            System.out.printf("%s.\n", e.getMessage());
        }

        fst.sort(Integer::compareTo);
        snd.sort(Integer::compareTo);

        for (int i = 0; i < fst.size(); i++) {
            totalDistance += Math.abs(fst.get(i) - snd.get(i));
        }

        System.out.printf("Solution part1: %d\n", totalDistance);
    }
}
