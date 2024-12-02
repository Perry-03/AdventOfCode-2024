import java.io.File;
import java.util.*;

public class Part2 {
    public static void main(String[] args) {
        int similarityScore = 0;
        Map<String, Integer> mem = new HashMap<>();
        List<String> fst = new ArrayList<>();

        File input = new File("Day01/input.txt");

        try (Scanner sc = new Scanner(input)) {
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split("\\s+");
                fst.add(line[0]);
                mem.merge(line[1], 1, Integer :: sum);
            }
        } catch (Exception e) {
            System.out.printf("%s.\n", e.getMessage());
        }

        for (String s : fst) similarityScore += Integer.parseInt(s) * mem.getOrDefault(s, 0);

        System.out.printf("Solution part2: %d\n", similarityScore);
    }
}
