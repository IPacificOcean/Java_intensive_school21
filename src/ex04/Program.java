package ex04;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
//        String str = "ABCAJHNXATGDBDGJSAMJHCGDMJH"; SHello, worlds!

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

        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] > 0) {
                System.out.println((char) i + ": " + charCount[i]);
            }
        }
        for (int i = 0; whatIsSimbol[i] != 0; i++) {
            System.out.print(whatIsSimbol[i] + " ");
        }
    }
    static void sort(int[] input) {
    }
}


