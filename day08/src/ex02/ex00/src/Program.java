public class Program {
    public static void main (String[] args) {

        int number = 479598;
        int sum;
        sum = number % 10;
        number /= 10;
        sum += number % 10;
        number /= 10;
        sum += number % 10;
        number /= 10;
        sum += number % 10;
        number /= 10;
        sum += number % 10;
        number /= 10;
        sum += number % 10;
        System.out.println("sum = "+ sum);
    }
}