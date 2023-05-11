package ex00;

import java.util.concurrent.TimeUnit;

public class MyThread extends Thread implements Runnable {
    int numbers_;
    String name_;

    public MyThread(String name, int numbers) {
        name_ = name;
        numbers_ = numbers;
    }

    @Override
    public void run() {
        try {
            loop(numbers_, name_);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void loop(int n, String obj) throws InterruptedException {
        for (int i = 0; i < n; ++i) {
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(obj);
        }
    }
}
