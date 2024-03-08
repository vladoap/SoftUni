package algorithms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleArraySum {
    static class Result {
        /*
         * Complete the 'simpleArraySum' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts INTEGER_ARRAY ar as parameter.
         */

        public static int simpleArraySum(List<Integer> ar) {
            return ar.stream().mapToInt(Integer::intValue).sum();

        }

    }


    public class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

            int arCount = Integer.parseInt(bufferedReader.readLine().trim());

            String[] arTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> ar = new ArrayList<>();

            for (int i = 0; i < arCount; i++) {
                int arItem = Integer.parseInt(arTemp[i]);
                ar.add(arItem);
            }

            int result = Result.simpleArraySum(ar);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
