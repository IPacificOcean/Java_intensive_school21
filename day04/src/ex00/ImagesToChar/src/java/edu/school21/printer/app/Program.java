package edu.school21.printer.app;

//import edu.school21.printer.logic.*;

import edu.school21.printer.logic.ConvertImgToConsoleView;
import edu.school21.printer.logic.Validation;

import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        Validation v = new Validation();
        v.check(args);
        ConvertImgToConsoleView img = new ConvertImgToConsoleView(args);
        try {
            img.convertAndOutputInConsole();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
