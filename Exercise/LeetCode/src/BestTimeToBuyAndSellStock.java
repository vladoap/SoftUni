public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {

//        int[] prices = {7,1,5,3,6,4};
        int[] prices = {2, 1, 2, 1, 0, 1, 2};
        int result = maxProfit(prices);
    }

    public static int maxProfit(int[] prices) {

        int smallest = Integer.MAX_VALUE;
        int overallProfit = 0;
        int currentProfit = 0;

        for (int i = 0; i < prices.length; i++) {
           if (prices[i] < smallest) {
               smallest = prices[i];
           }

           currentProfit = prices[i] - smallest;
           if (currentProfit > overallProfit) {
               overallProfit = currentProfit;
           }

        }
        return overallProfit;
    }
}
