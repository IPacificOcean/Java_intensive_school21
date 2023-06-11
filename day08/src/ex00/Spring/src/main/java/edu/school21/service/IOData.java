package edu.school21.service;

import edu.school21.interfaces.PreProcessor;
import edu.school21.interfaces.Printer;
import edu.school21.interfaces.Renderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOData {
    public void output() {
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix("Prefix");
        printer.print("Hello!");
        System.out.println("Without using a spring");

        System.out.println("______________________________");
        System.out.println();

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        Printer printerWithPrefixErrToUpper =
                context.getBean("printerWithPrefixErrToUpper", Printer.class);
        System.out.println("With using a spring");
        printerWithPrefixErrToUpper.print("Hello!");
    }
}
