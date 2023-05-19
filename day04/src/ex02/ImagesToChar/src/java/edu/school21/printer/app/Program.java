package edu.school21.printer.app;

import edu.school21.printer.logic.*;


import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        String  pathToImg = "target/resources/it.bmp";
        Validation v = new Validation();
        ParserCL pcl = new ParserCL();
        v.check(args, pathToImg);
        pcl.commandLineParser(args);
        ConvertImgToConsoleView img = new ConvertImgToConsoleView(pcl, pathToImg);
        try {
            img.convertAndOutputInConsole();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
