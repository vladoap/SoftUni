public class BestTimeToBuyAndSellStock2 {


    public static void main(String[] args) {

        int[] prices = {1,2,3,4,5};
        int result = maxProfit(prices);
    }

    public static int maxProfit(int[] prices) {
        int result = 0;
        int overallProfit = 0;
        int currentProfit = 0;
        int smallest = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {
            if (i > 0) {
                if (prices[i] < prices[i - 1]) {
                    result += overallProfit;
                    currentProfit = 0;
                    overallProfit = 0;
                    smallest = prices[i];
                }
            }

            if (prices[i] < smallest) {
                smallest = prices[i];
            }

            currentProfit = prices[i] - smallest;

            if (currentProfit > overallProfit) {
                overallProfit = currentProfit;
            }
        }
        result += overallProfit;

        return result;
    }
}
