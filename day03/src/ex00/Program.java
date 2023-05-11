package ex00;


public class Program {
    public static void main(String[] args) {
        Validation v = new Validation();

        int numbers = v.check(args);

        MyThread egg = new MyThread("Egg", numbers);
        MyThread hen = new MyThread("Hen", numbers);

        egg.start();
        hen.start();

        try {
            egg.join();
            hen.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < numbers; ++i) {
            System.out.println("Human");
        }
    }
}
