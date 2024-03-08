import java.util.Scanner;

public class PrimeNumbers {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int num = Integer.parseInt(scanner.nextLine());
        boolean flag = false;


        for (int i = 2; i <= num / 2 ; i++) {
            if (num % i == 0) {
                flag = true;
                break;
            }
        }


        if (!flag && num >= 2) {
            System.out.println(num + " is a prime number");
        } else {
            System.out.println(num + " isn't a prime number");
        }
    }
}
