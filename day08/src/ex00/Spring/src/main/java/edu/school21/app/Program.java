package edu.school21.app;

import edu.school21.interfaces.PreProcessor;
import edu.school21.interfaces.Printer;
import edu.school21.interfaces.Renderer;
import edu.school21.service.PreProcessorToUpperImpl;
import edu.school21.service.PrinterWithDateTimeImpl;
import edu.school21.service.PrinterWithPrefixImpl;
import edu.school21.service.RendererErrImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        PrinterWithDateTimeImpl printer2 = new PrinterWithDateTimeImpl(renderer);
        printer.setPrefix("Prefix");
        printer.print("Hello!");
        printer2.print("Hello!");

        System.out.println("______________________________");

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        Printer printerWithDateErrToUpper = context.getBean("printerWithDateErrToUpper", Printer.class);
        printerWithDateErrToUpper.print("Hello!");
        System.out.println("______________________________");

        Printer printerWithDateStdToUpper = context.getBean("printerWithDateStdToUpper", Printer.class);
        printerWithDateStdToUpper.print("Hello!");


        Printer printerWithPrefixStdToUpper =
                context.getBean("printerWithPrefixStdToUpper", Printer.class);
        printerWithPrefixStdToUpper.print("Hello!");

        Printer printerWithPrefixErrToUpper =
                context.getBean("printerWithPrefixErrToUpper", Printer.class);
        printerWithPrefixErrToUpper.print("Hello!");

    }
}
