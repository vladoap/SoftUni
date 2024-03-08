import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Java_BigDecimal {

    public static void main(String[] args) {

        //Input
        Scanner sc= new Scanner(System.in);
        int n=sc.nextInt();
        String []s=new String[n+2];
        for(int i=0;i<n;i++){
            s[i]=sc.next();
        }
        sc.close();

        Arrays.sort(s, 0, n, Collections.reverseOrder((a1, a2) -> {
            //convert to big decimal inside comparator
            //so permanent string values are never changed
            //aka you only use the BigDecimal values to
            //compare the strings!
            BigDecimal a = new BigDecimal(a1);
            BigDecimal b = new BigDecimal(a2);
            return a.compareTo(b);
        }));

        //Output
        for(int i=0;i<n;i++)
        {
            System.out.println(s[i]);
        }
    }
}
