package ex03;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Program {
    public static void main(String[] args) throws Exception {
        Validation v = new Validation();
        v.check(args);
        int threadCount = v.getThreadCount_();

        Downloader d = new Downloader();
        d.txtToList();

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        int i = 0;
        while (i < 10) {
            executor.submit(() -> {
                try {
                    d.download();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            ++i;
        }
        executor.shutdown();

    }



}
