package ex02;


import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class Program {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Validation v = new Validation();
        v.check(args);

        int arraySize = v.getSize_();
        int threadCount = v.getThreadCount_();

        ArrayCreator arrCr = new ArrayCreator(arraySize);
        int[] array = arrCr.getRandomArray();

        System.out.println("Sum: " + Arrays.stream(array).sum());

        int threadsSum = new Summator().SumByThread(array, threadCount);
        System.out.printf("Sum by threads: %d", threadsSum);


    }
}
