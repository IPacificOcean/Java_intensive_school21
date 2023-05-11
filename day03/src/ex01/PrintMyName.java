package ex01;

import java.util.concurrent.TimeUnit;

public class PrintMyName implements Runnable {
    int numbers_;
    String name_;
    final Object monitor_;
    public PrintMyName(String name, int numbers, Object monitor) {
        name_ = name;
        numbers_ = numbers;
        monitor_ = monitor;
    }

    @Override
    public void run() {
        try {
            loop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
       void loop() throws InterruptedException {
        for (int i = 0; i < numbers_; ++i) {
            synchronized (monitor_) {
                TimeUnit.MILLISECONDS.sleep(100);
                monitor_.notify();
                System.out.println(name_);
                if (i != numbers_ - 1) {
                    monitor_.wait();
                }
            }
        }

    }
// create and run a thread
    public void start() {
        new Thread(this).start();
    }
}
