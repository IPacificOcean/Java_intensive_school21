package ex02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Summator {


    private int first_;
    private int last_;
    private final List<Future<Integer>> intermediateAmounts_ = new ArrayList<>();

    int SumByThread(int[] arr, int threadCount_) throws ExecutionException, InterruptedException {
        ExecutorService executorService_ = Executors.newFixedThreadPool(threadCount_);
        int lastTread = threadCount_ - 1;
        int countItems = arr.length / threadCount_;
        for (int i = 0; i < threadCount_; ++i) {
            first_ = countItems * i;
            if (i == lastTread) {
                last_ = arr.length;
            } else {
                last_ = countItems + countItems * i;
            }
            executorThreads(arr, i, executorService_);
        }
        executorService_.shutdown();

        return finalAmount();
    }

    private void executorThreads(int[] arr, int index, ExecutorService executorService_) {
        int begin = first_;
        int end = last_;
        intermediateAmounts_.add(executorService_.submit(() -> {
            int intermedSum = Arrays.stream(arr, begin, end).sum();
            String threadName = Thread.currentThread().getName();
            System.out.printf("Thread %d( %s ): from %d to %d sum is %d\n", index + 1, threadName, begin, end, intermedSum);
            return intermedSum;
        }));
    }

    private int finalAmount() throws ExecutionException, InterruptedException {
        int amount = 0;
        for (Future<Integer> item : intermediateAmounts_) {
            amount += item.get();
        }
        return amount;
    }
}
