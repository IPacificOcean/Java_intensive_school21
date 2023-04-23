package ex04;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
//        String str = "ABCAJHNXATGDBDGJSAMJHCGDMJH"; HHello, worl!   GACDDDAABBEEEEF

        Scanner scaner = new Scanner(System.in);
        String str = scaner.nextLine();
        scaner.close();

        char[] input = str.toCharArray();
        int[] symbolCount = new int[65535];
        char[] whatIsSymbol = new char[65535];
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = input[i];
            if (symbolCount[c] == 0) {
                whatIsSymbol[index] = c;
                ++index;
            }
            symbolCount[c]++;
        }
        int length = getOnlyLenArrayOfCharacters(whatIsSymbol);
        sort(whatIsSymbol, symbolCount, length, true);
        sort(whatIsSymbol, symbolCount, length, false);

        //Print//
        double count_sharps = 10;
        double coofficient = symbolCount[whatIsSymbol[0]] / count_sharps;

        System.out.println(coofficient);
        for (int i = 0; i < 10; ++i){
            if (whatIsSymbol[i] == 0) {
                break;
            }
            System.out.print(symbolCount[whatIsSymbol[i]] + ":" + (int)(symbolCount[whatIsSymbol[i]] / coofficient) + " ");
        }
            System.out.println();


        for (int i = 0; whatIsSymbol[i] != 0; i++) {
            System.out.print(whatIsSymbol[i] + " ");
        }
        System.out.println();
        printHistogram();

    }


    static void sort(char[] input, int[] charCount, int length, boolean lex_sort) {
        for (int i = 0; i < length - 1; ++i) {
            boolean swapped = false;
            for (int j = 0; j < length - i - 1; ++j) {
                if (lex_sort) {
                    if (input[j] > input[j + 1]) {
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

    static boolean swap(char[] input, int j) {
        char temp = input[j];
        input[j] = input[j + 1];
        input[j + 1] = temp;
        return true;
    }

    static int getOnlyLenArrayOfCharacters(char[] input) {
        int length = 0;
        for (char c : input) {
            if (c == 0) {
                break;
            }
            length++;
        }
        return length;
    }

    static void printHistogram(){
//        double count_sharps = 10;
//        double coofficient = symbolCount[whatIsSymbol[0]] / count_sharps;
        double coofficient = 3.6;

        int num[] = {36, 36, 36, 36, 0, 0, 0, 0, 0, 0};


        for (int i = 0; i < 10; ++i) {
            if (num[i] == num[0]) {
                System.out.printf("%-3d", num[i]);
            }
        }
//        System.out.println();
//            for (int j = 0; j < 10; ++j) {
//                System.out.printf("%-3c\n", '#');
//            }

        for (int i = 0; i < 10; i++) {
            int count_sharps = (int)(num[i] / coofficient);

            for (int j = 0; j < count_sharps; j++) {
                System.out.print("#");
            }

            System.out.printf("%-3d", num[i]);
            System.out.println();
        }

//        for (int i = 0; i < 2; i++) {
//            int count_sharps = (int)(num[i] / coofficient);
//
//            for (int j = 0; j < count_sharps; j++) {
//                System.out.print("#");
//            }
//
//            System.out.printf("%-3d", num[i]);
//            System.out.println();
//        }
    }

//    public static void paintTable(char[] chars, int[] counts) {
//        int d = counts[0];
//        System.out.println();
//        System.out.println();
//
//        for (int i = 0; i < 10; i++) {
//            if (counts[i] == d)
//                System.out.print(counts[i] + "\t");
//        }
//        System.out.println();
//        for (int i = 10; i > 0; i--) {
//            for (int j = 0; j < 10; j++) {
//                if (counts[j] * 10 / d >= i)
//                    System.out.print("#\t");
//                if (counts[j] * 10 / d == i - 1) {
//                    if (counts[j] != 0) {
//                        System.out.print(counts[j] + "\t");
//                    }
//                }
//            }
//            System.out.println();
//        }
//        for (int i = 0; i < 10; i++) {
//            System.out.print(chars[i] + "\t");
//        }
//    }

}




