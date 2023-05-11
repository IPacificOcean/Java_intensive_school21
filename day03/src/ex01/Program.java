package ex01;


public class Program {
    public static void main(String[] args) {
        Validation v = new Validation();
        int numbers = v.check(args);

        Object monitor = new Object();
        PrintMyName egg = new PrintMyName("Egg", numbers, monitor);
        PrintMyName hen = new PrintMyName("Hen", numbers, monitor);
        egg.start();
        hen.start();
    }
}
