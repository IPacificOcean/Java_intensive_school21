package ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int number;
        int counter_requests = 0;
        int sum_elem;
        while (true) {
            System.out.println("Input some number");
            number = scanner.nextInt();
            if (number <= 1) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            sum_elem = sumDigits(number);
            System.out.println(isPrime(sum_elem));
            if (isPrime(sum_elem)) {
                counter_requests++;
            }
            if (number == 42) {
                System.out.println("Count of coffee - request - " + counter_requests);
                System.exit(0);
            }
        }
    }

    public static int sumDigits(int number) {
        int sum = number % 10;

        while (number != 0) {
            number /= 10;
            sum += number % 10;
        }

        System.out.println("sum = " + sum);
        return sum;
    }

    public static boolean isPrime(int number) {
        boolean res = true;

        if (number == 2) {
            return res;
        }
        if ((number % 2) == 0) {
            return !res;
        }

        for (int i = 3; i * i <= number; i += 2) {
            if ((number % i) == 0) {
                return !res;
            }
        }

        return res;
    }

}
