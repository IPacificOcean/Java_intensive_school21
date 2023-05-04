package ex00;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CheckExtension {
    String in_;
    String result_;
    HashMap<String, String> dictCodes = new HashMap<>();
    int maxLengthLine = 0;

    public CheckExtension(String in, String result) {
        this.in_ = in;
        this.result_ = result;
    }

    void startApp() {
        getSignatures();
        checkExtensionOfFiles();
    }
void getSignatures(){
    try (FileReader fileIn = new FileReader(in_)) {
        Scanner scanner = new Scanner(fileIn);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            String[] strExtAndCode = str.split(",");
            String strOfCode = strExtAndCode[1].trim();
            maxLengthLine = Math.max(strOfCode.length(), maxLengthLine);
            dictCodes.put(strOfCode, strExtAndCode[0]);
        }
        scanner.close();
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}

void checkExtensionOfFiles() {
    String strPath;
    Scanner scannerFout = new Scanner(System.in);
    while (scannerFout.hasNext()) {
        strPath = scannerFout.nextLine();
        if (strPath.equals("42")) break;
        String strSourse = getStrForCheck(strPath);
        String strExt = getExtension(strSourse);
        writeExtInFile(strExt);
    }
    scannerFout.close();

}

String getStrForCheck(String strPath) {
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
    return strB.toString();
}

    String getExtension(String strSourse) {
        String Ext = null;
        for (Map.Entry m : dictCodes.entrySet()) {
            if (strSourse.startsWith((String) m.getKey())) {
                Ext = m.getValue() + "\n";
            }
        }
        return  Ext;
    }

    void writeExtInFile(String strExt) {
        try (FileWriter fileOut = new FileWriter(result_, true)) {
            if (strExt != null) {
                fileOut.write(strExt);
                System.out.println("PROCESSED");
            } else {
                System.out.println("UNDEFINED");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
