package ex00;

import java.util.concurrent.TimeUnit;


public class Program {
    public static void main(String[] args) throws InterruptedException {
        Validation v = new Validation();

        int numbers = v.check(args);
        Thread egg = new Thread(() -> {
            try {
                loop(numbers, "Egg");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread hen = new Thread(() -> {
            try {
                loop(numbers, "Hen");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

            egg.start();
            hen.start();

        try {
            egg.join();
            hen.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        loop(numbers, "Human");

    }

    static void loop(int n, String obj) throws InterruptedException {
        for (int i = 0; i < n; ++i) {
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(obj);
        }
    }
}
