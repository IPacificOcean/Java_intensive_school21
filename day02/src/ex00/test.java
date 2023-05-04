package ex00;

/*
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class test {
    public static void main(String[] args) throws IOException {
        String in = "day02/src/ex00/signatures.txt";
        FileReader fileIn = new FileReader(in);
        HashMap<String, String> dictCodes = new HashMap<>();
        int maxLengthLine = 0;
        Scanner scanner = new Scanner(fileIn);
// Ищем максимальную длину строки и записываем в map- ключ:значение
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            String[] strExtAndCode = str.split(",");
            String strOfCode = strExtAndCode[1].trim();
            maxLengthLine = Math.max(strOfCode.length(), maxLengthLine);
            dictCodes.put(strOfCode, strExtAndCode[0]);
//            System.out.println(strExtAndCode[0] + ": :" + strOfCode + ':');
        }
//        System.out.println("maxLengthLine: " + maxLengthLine);
        scanner.close();
//Получаем путь к файлу для распознавания
        String strPath;
        Scanner scannerFout = new Scanner(System.in);
        while (scannerFout.hasNext()) {
            strPath = scannerFout.nextLine();
            if (strPath.equals("42")) break;
//            System.out.println(strPath);
//Считываем из файла источника первые maxLengthLine байт и записываем в строку
            StringBuilder strB = new StringBuilder();
            try (FileReader fileIn2 = new FileReader(strPath)) {

                int a;
                int length = maxLengthLine;
                while ((a = fileIn2.read()) != -1) {
                    strB.append(String.format("%02X ", a));
                    if (length == 0) {
                        strB.append("\n");
                        break;
                    }
                    --length;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            // сравниваем строку из файла источника с ключем в словаре и записываем в файл значение(расширение файла)
            String strSourse = strB.toString();
//            System.out.println();

            String strPref = null;
            for (Map.Entry m : dictCodes.entrySet()) {
                if (strSourse.startsWith((String) m.getKey())) {
                    strPref = m.getValue() + "\n";
//                    System.out.println(strPref);
                }
            }
            try (FileWriter fileOut = new FileWriter("day02/src/ex00/result.txt", true)) {
                if (strPref != null) {
                    fileOut.write(strPref);
                    System.out.println("PROCESSED");
                } else {
                    System.out.println("UNDEFINED");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
            scannerFout.close();


//        day02/src/ex00/pay.pdf
//        day02/src/ex00/image.png
//        day02/src/ex00/bubl.gif
    }







   */
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
            fileOut = new FileWriter("day02/src/ex00/result.txt");

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
    }*//*




*/
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
    }*//*

}
*/
