package edu.school21.app;

import edu.school21.interfaces.PreProcessor;
import edu.school21.interfaces.Renderer;
import edu.school21.service.PreProcessorToUpperImpl;
import edu.school21.service.PrinterWithDateTimeImpl;
import edu.school21.service.PrinterWithPrefixImpl;
import edu.school21.service.RendererErrImpl;

public class Program {
    public static void main(String[] args) {
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        PrinterWithDateTimeImpl printer2 = new PrinterWithDateTimeImpl(renderer);
        printer.setPrefix("Prefix");
        printer.print("Hello!");
        printer2.print("Hello!");
    }
}
