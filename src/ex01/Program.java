package ex01;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int number;
        int counter = 0;
        System.out.println("input some number");
        number = scanner.nextInt();
        if (number <= 1) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
        if (number == 2) {
            counter++;
            System.out.println("number of steps = " + counter);
            System.out.println("true");
            System.exit(0);
        }
        if ((number % 2) == 0) {
            counter++;
            System.out.println("number of steps = " + counter);
            System.out.println("false");
            System.exit(0);
        }
        /*When we're checking if a number is prime or not, we're looking to see
        if it has any factors other than 1 and itself. If a number
        has factors other than 1 and itself, then it is not prime.
        If a number only has factors of 1 and itself, then it is prime.
        (Factors are numbers that can be multiplied together to get another number.
        For example, the factors of 12 are 1, 2, 3, 4, 6, and 12,
        because these are all the numbers that can be multiplied together to get 12:

        1 x 12 = 12
        2 x 6 = 12
        3 x 4 = 12
        )
         * */
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            counter++;
            int x = number % i;
            if (x == 0) {
                System.out.println("number of steps = " + counter);
                System.out.println("false");
                System.exit(0);
            }
        }

        System.out.println("number of steps = " + counter);
        System.out.println("true");
    }
}

// To check if a number is prime, you can follow these steps:
//
//1. Start with the number you want to check if it's prime.
//2. Check if the number is less than or equal to 1. If it is, then it is not prime.
//3. Check if the number is 2. If it is, then it is prime.
//4. Check if the number is even. If it is, then it is not prime.
//5. Iterate from 3 to the square root of the number (inclusive) in steps of 2.
//6. For each iteration, check if the number is divisible by the iteration number. If it is, then it is not prime.
//7. If the number has not been determined to be composite by step 6, then it is prime.
