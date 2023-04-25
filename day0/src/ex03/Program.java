package ex03;

import java.util.Scanner;
//import java.io.ByteArrayInputStream;

public class Program {
    public static void main(String[] args) {
        /* for testing */
//        String buffer = "Week 1\n1 2 3 4 5\nWeek 2\n6 2 3 4 5\nWeek 3\n9 7 3 4 5\nWeek 4\n9 9 9 4 5\nWeek 5\n9 9 9 9 5\nWeek 6\n6 7 8 9 9\nWeek 7\n7 9 9 9 9\nWeek 8\n8 9 9 9 9\nWeek 9\n1 2 3 4 5\nWeek 10\n1 2 3 4 5\nWeek 11\n1 2 3 4 5\nWeek 12\n1 2 3 4 5\nWeek 13\n1 2 3 4 5\nWeek 14\n1 2 3 4 5\nWeek 15\n1 2 3 4 5\nWeek 16\n1 2 3 4 5\nWeek 17\n1 2 3 4 5\nWeek 18\n1 2 3 4 5\n";
//        System.setIn(new ByteArrayInputStream(buffer.getBytes()));
        /* for testing */

        Scanner scanner = new Scanner(System.in);
        String week;
        int weeks_count = 0;
        long grade;
        long grade_store = 0;

        for (int i = 0; i < 18; i++) {
            week = inputWeekNumber(scanner);
            weeks_count++;

            if (week.equals("42")) {
                break;
            }

            conditionsOfExit(week, i);

            grade = inputGrade(scanner);

            grade_store += storeInDigit(i, grade);

        }
        gradeDiogram(grade_store, weeks_count);
        scanner.close();

    }

    static String inputWeekNumber(Scanner scanner) {
        System.out.println("Input day of week:");
        return scanner.nextLine();
    }

    static void conditionsOfExit(String week, int iter) {

        if (!week.equals("Week " + (iter + 1))) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
    }

    static long storeInDigit(int iter, long grade) {
        int j = iter;
        long digit_position = 1;
        while (j != 0) {
            digit_position *= 10;
            j--;
        }
        return grade * digit_position;
    }

    static int inputGrade(Scanner scanner) {
        System.out.println("Input grade:");
        int grade = scanner.nextInt();
        validNumber(grade);
        for (int i = 0; i < 4; ++i) {
            int next_grade = scanner.nextInt();
            validNumber(next_grade);
            grade = min(grade, next_grade);
        }
        scanner.nextLine();
        return grade;
    }

    static void validNumber(int grade) {
        if (grade < 1 || grade > 9) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
    }

    static int min(int a, int b) {
        return a < b ? a : b;
    }

    static void gradeDiogram(long grade_store, int weeks_count) {
        for (int i = 1; i <= weeks_count; i++) {
            int grade = (int) (grade_store % 10);
            grade_store /= 10;

            System.out.print("Week " + i + " ");
            for (int j = 0; j < grade; j++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }
}
