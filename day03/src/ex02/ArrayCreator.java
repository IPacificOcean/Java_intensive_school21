package ex02;

import java.util.SplittableRandom;

public class ArrayCreator {
    int arraySize_;

    public ArrayCreator(int arraySize) {
        arraySize_ = arraySize;
    }


    int[] getRandomArray() {

        return new SplittableRandom().ints(arraySize_, -1000, 1000).parallel().toArray();
    }
}

//    int n = new SplittableRandom().nextInt(0, 11);