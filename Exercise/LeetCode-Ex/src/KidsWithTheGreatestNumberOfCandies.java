import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KidsWithTheGreatestNumberOfCandies {

    public static void main(String[] args) {

        int[] candies = {2,3,5,1,3};
        int extraCandies = 3;

        List<Boolean> result = kidsWithCandies(candies, extraCandies);
        result.forEach(e -> System.out.print(e + " "));
    }

    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Arrays.stream(candies).max().getAsInt();
        List<Boolean> result = new ArrayList<>();

        for (int candy : candies) {
            if (candy + extraCandies >= max) {
                result.add(true);
            } else {
                result.add(false);
            }
        }

        return result;
    }
}
