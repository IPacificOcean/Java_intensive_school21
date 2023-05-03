package ex00;

import java.io.*;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class test {
    public static void main(String[] args) throws IOException {
        FileReader fileIn = new FileReader("day02/src/ex00/signatures.txt");
        FileWriter fileOut = new FileWriter("day02/src/ex00/copy_signatures.txt");
        int maxCountLine = 0;
        Scanner scanner = new Scanner(fileIn);
// Ищем максимальную длину строки
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            String[] strEXT = str.split(",");
            String str2 = strEXT[1].trim();
            maxCountLine = Math.max(str2.length(), maxCountLine);
            System.out.println(strEXT[0] + ": :" + str2+ ':' );
        }
        System.out.println("maxCountLine: " + maxCountLine);

        scanner.close();
//Получаем путь к файлу для распознавания
        Scanner scannerFout = new Scanner(System.in);
        String str = scannerFout.nextLine();
        System.out.println(str);
        scannerFout.close();
//Считываем из файла источника первые maxCountLine байт и записываем в строку
        FileReader fileIn2 = new FileReader(str);

        StringBuilder strB = new StringBuilder();
        int a;
        int i=0;
        while ((a = fileIn2.read()) != -1) {
            strB.append(String.format("%02X ", a));
            if (maxCountLine == 0) break;
            --maxCountLine;

        }
        if (fileIn2 != null) {
            fileIn2.close();
        }
        if (fileOut != null) {
            fileOut.close();
        }
        String str3 = strB.toString();
            System.out.println(str3);


//        day02/src/ex00/pay.pdf
//        day02/src/ex00/image.png
}
   /* public static void main(String[] args) throws IOException {


        FileReader fileIn = null;
        FileWriter fileOut = null;
//        File t = new File("day02/src/ex00/signatures.txt");
//        InputStream c = new FileInputStream(t);
        int countEndL = 0;
        int maxCountLine = 0;
String str;
        try {

            fileIn = new FileReader("day02/src/ex00/signatures.txt");
            fileOut = new FileWriter("day02/src/ex00/copy_signatures.txt");

            int a;
            while ((a = fileIn.read())!= -1) {
                if (a == '\n') countEndL++;
                fileOut.write(a);

            }
            System.out.println(countEndL);
        } finally {
            if (fileIn != null) {
                fileIn.close();
            }
            if (fileOut != null) {
                fileOut.close();
            }
        }
    }*/



/*    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            System.out.println(new Date() + "\n");
//            Thread.sleep(10000); // Замораживает весь поток на 10 секунд
//            TimeUnit.MILLISECONDS.sleep(10000);
            System.out.println(new Date() + "\n");
            long timeWorkCode = System.currentTimeMillis() - start;
            System.out.println("Скорость выполнения программы: " + timeWorkCode + " миллисекунд");
        } catch (Exception e) {
            System.out.println("Получили исключение!");
        }
    }*/
}
