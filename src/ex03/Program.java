package ex03;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String week;
//        String output = "";
        int weeks_number = 0;
        int grade;
        int grade_store = 0;
        for (int i = 0; i < 18; i++) {
            week = inputWeek(scanner);
            weeks_number = i;
            if (week.equals("42")) {
                break;
            }

            grade = inputGrade(scanner);

            grade_store += storeInDigit(i, grade);


        }
            plotGrade(grade_store, weeks_number);
//        scanner.close();

    }

    static String inputWeek(Scanner scanner) {
        System.out.println("Input day of week:");
        return scanner.next();
    }


    static int storeInDigit(int iter, int grade) {
        int j = iter;
        int digit_position = 1;
        while (j != 0) {
            digit_position *=10;
            j--;
        }
        return grade * digit_position;
    }

    static int inputGrade(Scanner scanner) {
        System.out.println("Input grade:");
        int grade = scanner.nextInt();
        for (int i = 0; i < 4; ++i) {
            int next_grade = scanner.nextInt();
            grade = min(grade, next_grade);
        }
        return grade;
    }
    static int min (int a, int b) {
        return a < b ? a : b;
    }
//    static void plotGrade(int grade, int iter) {
//        for (int i = 1; i <= iter; i++) {
//            System.out.print("Week " + i + " ");
//            for (int j = 0; j < grade; j++) {
//                System.out.print("=");
//            }
//            System.out.println(">");
//        }
//    }

    static void plotGrade(int grade_store, int iter) {
        for (int i = 1; i <= iter; i++) {
            int grade = grade_store % 10;
            grade_store /= 10;

            System.out.print("Week " + i + " ");
            for (int j = 0; j < grade; j++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }
}
