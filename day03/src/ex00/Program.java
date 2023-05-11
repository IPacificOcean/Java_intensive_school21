package ex00;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class Program {
    public static void main(String[] args) {
//        if((args.length >= 1) && args[0].startsWith("--current-folder=")) {
//            String workdir = args[0].substring("--current-folder=".length());
////            startApp.start();
//        } else {
//            System.err.println("too few arguments or error of input");
//        }


        int numbers = 50;
        Thread egg = new Thread(() -> {
            try {
                foo(numbers, "Egg");
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread hen = new Thread(() -> {
            try {
                foo(numbers, "Hen");
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

            try {
            egg.start();
            hen.start();
            egg.join();
            hen.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        for (int i= 0; i < numbers; ++i) {
            System.out.println("Human " + Thread.currentThread().getName());
        }
    }



       static void foo (int n, String obj) throws InterruptedException {
           for (int i= 0; i < n; ++i) {
               String name = Thread.currentThread().getName();
               TimeUnit.MILLISECONDS.sleep(10);
               System.out.println(obj + name);
           }
        }








//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        for (int i = 0; i < 5; ++i) {
//            executor.submit(() -> {
//                String threadName = Thread.currentThread().getName();
////
//                System.out.println("Egg " + threadName);
//            });
//
//        System.out.println("Hello, Main");
//        }
//        executor.shutdown();
}
