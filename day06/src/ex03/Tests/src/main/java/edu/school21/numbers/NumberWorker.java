package edu.school21.numbers;


public class NumberWorker {
    public boolean isPrime(int number) {
        boolean isPrime = true;
        if (number <= 1) {
            throw new IllegalNumberException("0, 1, or negative is illegal arguments");
        }
        if (number != 2) {

            if ((number % 2) == 0) {
                isPrime = false;
            } else {
                for (int i = 3; i * i <= number; i += 2) {
                    if ((number % i) == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }
        }
        return isPrime;
    }

    public int digitSum(int number) {
        number = Math.abs(number);
        int sum = number % 10;
        while (number != 0) {
            number /= 10;
            sum += number % 10;
        }
        return sum;
    }
}
