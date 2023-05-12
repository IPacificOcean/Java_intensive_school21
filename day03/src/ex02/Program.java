package ex02;


public class Program {
    public static void main(String[] args) {
        Validation v = new Validation();
        v.check(args);
        int arraySize_ = v.getSize_();
        int threadCount_ = v.getThreadCount_();
        System.out.println(arraySize_ + " " + threadCount_);
        ArrayCreator arrCr = new ArrayCreator(arraySize_);
        int[] array = arrCr.getRandomArray();
        for (int j : array) {
            System.out.println(j);
        }
    }
}
