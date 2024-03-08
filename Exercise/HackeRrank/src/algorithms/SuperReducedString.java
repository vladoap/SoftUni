package algorithms;

import java.io.*;
import java.util.PriorityQueue;

public class SuperReducedString {
    static class Result {


        /*
         * Complete the 'superReducedString' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts STRING s as parameter.
         */

        public static String superReducedString(String s) {
            StringBuilder sb = new StringBuilder(s);
            for (int i = 1; i < sb.length(); i++) {
                if (sb.charAt(i) == sb.charAt(i - 1)) {
                    sb.delete(i - 1, i + 1);
                    i = 0;
                }
            }

            if (sb.isEmpty()) {
                return "Empty String";
            } else {
                return sb.toString();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = bufferedReader.readLine();

        String result = Result.superReducedString(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();


    }
}

