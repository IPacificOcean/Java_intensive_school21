package ex01;

import java.io.FileWriter;
import java.io.IOException;

public class CosineSimilarityOfTexts {
    private String dictPath = "day02/src/ex01/Dictionary.txt";
    int[] a = {1, 1, 0, 2, 1, 0};
    int[] b = {3, 0, 1, 1, 0, 1};

    public void similarity() {
        int numeratorAB = numeratorAB();
        double denominator = denominator();
        double similarity = roundWithPrecision((numeratorAB / denominator), 100);// todo checklist

        System.out.println(numeratorAB);
        System.out.println(denominator);
        System.out.println(similarity);
    }

    double denominator() {
        int arrB = 0;
        int arrA = 0;
        double numeratorAB;
        double degree = 2;
        for (int i = 0; i < a.length; ++i) {
            arrA += (Math.pow(a[i], degree));
            arrB += (Math.pow(b[i], degree));
        }
        numeratorAB = (roundWithPrecision(Math.sqrt(arrA), 100))
                * (roundWithPrecision(Math.sqrt(arrB), 100));
        return roundWithPrecision(numeratorAB, 10);
    }

    double roundWithPrecision(double r, double units) {
        return Math.floor(r * units) / units;
    }

    int numeratorAB() {
        int ab = 0;
        for(int i =0; i < a.length; ++i) {
            ab += a[i] * b[i];
        }
        return ab;
    }


    private void writeExtInFile(String strExt) {
        try (FileWriter fileOut = new FileWriter(dictPath, true)) {
            if (strExt != null) {
                fileOut.write(strExt);
                System.out.println("PROCESSED");
            } else {
                System.out.println("UNDEFINED");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
