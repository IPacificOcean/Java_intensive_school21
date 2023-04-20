package ex04;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
//        String str = "ABCAJHNXATGDBDGJSAMJHCGDMJH"; HHello, worl!   GACDDDAABBEEEEF

        Scanner scaner = new Scanner(System.in);
        String str = scaner.nextLine();
        System.out.println(str);
        scaner.close();

        char[] input = str.toCharArray();
        int[] charCount = new int[65535];
        char[] whatIsSimbol = new char[65535];
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = input[i];
            if (charCount[c] == 0) {
                whatIsSimbol[index] = c;
                ++index;
            }
            charCount[c]++;
        }
        int length = getLenOnlyArrayOfCharacters(whatIsSimbol);
        sort(whatIsSimbol, charCount, length, true);
        sort(whatIsSimbol, charCount, length, false);

        //Print//
        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] > 0) {
                System.out.println((char) i + ": " + charCount[i]);
            }
        }
        for (int i = 0; whatIsSimbol[i] != 0; i++) {
            System.out.print(whatIsSimbol[i] + " ");
        }
    }

    static void sort(char[] input, int[] charCount, int length, boolean lex_sort) {
        boolean swapped = false;
        for (int i = 0; i < length - 1; ++i) {
            swapped = false;
            for (int j = 0; j < length - i - 1; ++j) {
                if (lex_sort) {
                    if (input[j] >input[j + 1]) {
                        swapped = swap(input, j);
                    }
                } else {
                    if (charCount[input[j]] < charCount[input[j + 1]]) {
                        swapped = swap(input, j);
                    }
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    static void sortByCount(char[] input, int[] charCount, int length) {
        boolean swapped = false;
        for (int i = 0; i < length - 1; ++i) {
            swapped = false;
            for (int j = 0; j < length - i - 1; ++j) {
                    if (charCount[input[j]] < charCount[input[j + 1]]) {
                        swapped = swap(input, j);
                    }
            }
            if (!swapped) {
                break;
            }
        }
    }
    static void lexsSort(char[] input, int length) {
        boolean swapped = false;
        for (int i = 0; i < length - 1; ++i) {
            swapped = false;
            for (int j = 0; j < length - i - 1; ++j) {
                    if (input[j] >input[j + 1]) {
                        swapped = swap(input, j);
                    }
            }
            if (!swapped) {
                break;
            }
        }
    }

    static boolean swap(char[] input, int j) {
        char temp = input[j];
        input[j] = input[j + 1];
        input[j + 1] = temp;
        return  true;
    }

    static int getLenOnlyArrayOfCharacters(char[] input) {
        int length = 0;
        for (char c : input) {
            if (c == 0) {
                break;
            }
            length++;
        }
        return length;
    }
}




