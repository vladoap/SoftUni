public class GasStation {

    public static void main(String[] args) {


        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        int result = canCompleteCircuit(gas, cost);
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }

        if (totalGas < totalCost) {
            return -1;
        }

        int remaining = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            remaining += gas[i] - cost[i];

            if (remaining < 0) {
                start = i + 1;
                remaining = 0;
            }
        }

        return start;
    }


}
