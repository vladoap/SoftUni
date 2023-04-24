import java.util.*;
import java.util.stream.Collectors;

public class P01 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayDeque<Integer> textile = new ArrayDeque();
        ArrayDeque<Integer> medicaments = new ArrayDeque();

        Arrays.stream(scan.nextLine().split("\\s+"))
                        .map(Integer::parseInt).
                forEach(e -> textile.offer(e));

        Arrays.stream(scan.nextLine().split("\\s+"))
                .map(Integer::parseInt).
                forEach(e -> medicaments.push(e));


        int countPatch = 0;
        int countBandage = 0;
        int countMedKit = 0;
        Map<String, Integer> craftedItems = new TreeMap<>();

        while (!textile.isEmpty() && !medicaments.isEmpty()) {

            int textileNumber = textile.peek();
            int medicamentNumber = medicaments.peek();
            int sum = textileNumber + medicamentNumber;
            boolean isCrafted = false;

            switch (sum) {
                case 30:
                    countPatch++;
                    craftedItems.put("Patch", countPatch);
                    isCrafted = true;
                    break;
                case 40:
                    countBandage++;
                    craftedItems.put("Bandage", countBandage);
                    isCrafted = true;
                    break;
                case 100:
                    countMedKit++;
                    craftedItems.put("MedKit", countMedKit);
                    isCrafted = true;
                    break;

            }

            if (isCrafted) {
                medicaments.pop();
                textile.poll();
            } else if (sum > 100){
                int remainingOfSum = sum - 100;
                medicaments.pop();
                textile.poll();
                int currentMed = medicaments.pop();
                medicaments.push(currentMed + remainingOfSum);
                countMedKit++;
                craftedItems.put("MedKit", countMedKit);
            } else {
                textile.poll();
                int currentMed = medicaments.pop();
                medicaments.push(currentMed + 10);
            }

        }

       if (textile.isEmpty() && medicaments.isEmpty()) {
           System.out.println("Textiles and medicaments are both empty.");
       } else if (textile.isEmpty()) {
           System.out.println("Textiles are empty.");
       } else {
           System.out.println("Medicaments are empty.");
       }

       craftedItems.entrySet().stream()
               .sorted((l, r) -> r.getValue() - l.getValue())
               .forEach(entry -> System.out.printf("%s - %d%n", entry.getKey(), entry.getValue()));


       if (!medicaments.isEmpty()) {
           String output = medicaments.stream().map(String::valueOf).collect(Collectors.joining(", "));
           System.out.printf("Medicaments left: %s%n", output);
       } else if (!textile.isEmpty()){
           String output = textile.stream().map(String::valueOf).collect(Collectors.joining(", "));
           System.out.printf("Textiles left: %s%n", output);
       }

    }
}
