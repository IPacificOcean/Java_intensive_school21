package ex04;

import java.sql.SQLOutput;

public class Program {
    public static void main(String[] args) {
//        String str = "ABCAJHNXATGDBDGJSAMJHCGDMJH";
        String output;

        String str = "Hello, world!";
        int[] charCount = new int[256]; // 256 is the maximum possible value for a char
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            charCount[c]++;
        }

        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] > 0) {
                System.out.println((char) i + ": " + charCount[i]);
            }
        }
    }
}
