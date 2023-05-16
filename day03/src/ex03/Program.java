package ex03;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program {
    public static void main(String[] args) {
        Validation v = new Validation();
        v.check(args);
        int threadCount = v.getThreadCount_();
        int numberOfFiles = 8;

        Downloader d = new Downloader();
        d.txtToList();

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        int i = 0;
        while (i < numberOfFiles) {
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
