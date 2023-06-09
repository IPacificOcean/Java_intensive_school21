package ex01;

import java.io.*;
import java.util.*;

public class CosineSimilarityOfTexts {
    private final String dictPath_;
    private final String textA_;
    private final String textB_;
    private final List<String> wordsA_ = new ArrayList<>();
    private final List<String> wordsB_ = new ArrayList<>();
    private final Set<String> dict_ = new HashSet<>();
    private int[] a_;
    private int[] b_;
    private double similarity_ = 0.0;
    private static final int BUFFER_SIZE = 10 * 1024 * 1024;


    public CosineSimilarityOfTexts(String textA, String textB, String dictPath) {
        textA_ = textA;
        textB_ = textB;
        dictPath_ = dictPath;
    }

    void startApp() {
        textToStringArray(textA_, wordsA_);
        textToStringArray(textB_, wordsB_);
        createDict();
        a_ = occurrence(wordsA_);
        b_ = occurrence(wordsB_);
        similarity();
        writeDictInFile();
    }

    void textToStringArray(String text, List<String> words) {
        try (BufferedReader reader = new BufferedReader(new FileReader(text), BUFFER_SIZE)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Collections.addAll(words, line.split("\\W+"));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void createDict() {
        dict_.addAll(wordsA_);
        dict_.addAll(wordsB_);
    }

    int[] occurrence(List<String> words) {
        int[] countOccurrence = new int[dict_.size()];
        int j = 0;
        int count;
        Iterator<String> i = dict_.iterator();
        String line;
        while (i.hasNext()) {
            line = i.next();
            count = 0;
            for (String s : words) {
                if (line.equals(s)) {
                    ++count;
                }
            }
            countOccurrence[j] = count;
            ++j;
        }
        return countOccurrence;
    }


    public void similarity() {
        int numeratorAB = numeratorAB();
        double denominator = denominator();
        if (denominator != 0) {
            similarity_ = roundWithPrecision((numeratorAB / denominator), 100);
        }
        System.out.println("Similarity = " + similarity_);
    }

    int numeratorAB() {
        int ab = 0;
        for (int i = 0; i < a_.length; ++i) {
            ab += a_[i] * b_[i];
        }
        return ab;
    }
    double denominator() {
        int arrB = 0;
        int arrA = 0;
        double numeratorAB;
        double degree = 2;
        for (int i = 0; i < a_.length; ++i) {
            arrA += (Math.pow(a_[i], degree));
            arrB += (Math.pow(b_[i], degree));
        }
        numeratorAB = (roundWithPrecision(Math.sqrt(arrA), 100))
                * (roundWithPrecision(Math.sqrt(arrB), 100));
        return roundWithPrecision(numeratorAB, 100);
    }

    double roundWithPrecision(double r, double units) {
        return Math.floor(r * units) / units;
    }
    private void writeDictInFile() {
        try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(dictPath_), BUFFER_SIZE)) {
            for (String s : dict_) {
                fileOut.write(s + " ");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
