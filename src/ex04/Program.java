package ex04;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
//        String str = "ABCAJHNXATGDBDGJSAMJHCGDMJH"; HHello, worl!   GACDDDAABBEEEEF aaaaaaaasssssssddddddfffffgggghhhjjklliiii

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
        printHistogram(whatIsSymbol, symbolCount, length);

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

    static void printHistogram(char[] simbols, int[] charCount, int length){
        int len = length <= 10 ? length : 10;
        double count_sharps = 10;
        double coofficient = charCount[simbols[0]] / count_sharps;

// первый блок печатает макс колл-во в одной строке
        for (int i = 0; i < 10; ++i) {
            if (charCount[simbols[i]] == charCount[simbols[0]]) {
                System.out.printf("%-3d", charCount[simbols[i]]);
            }
        }
        System.out.println();
// второй блок печатает шарп и колл-во в одной строке
        for (int i = 10; i > 0; i--) {

            for (int j = 0; j < 10; j++) {
                if ((int)(charCount[simbols[j]] / coofficient) >= i) {
                System.out.printf("%-3c", '#');
                }
                if ((int)(charCount[simbols[j]] / coofficient) == (i-1)) {
                        System.out.printf("%-3d", charCount[simbols[j]]);
                }
            }
            System.out.println();
        }
// третий блок печатает символы в одной строке
        for (int i = 0; i < len ; ++i) {
            System.out.printf("%-3c",simbols[i]);

        }
    }

}




