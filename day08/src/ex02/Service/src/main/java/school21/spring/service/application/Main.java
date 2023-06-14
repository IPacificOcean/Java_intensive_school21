package school21.spring.service.application;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.IOData.OutputStandard;
import school21.spring.service.IOData.OutputTamplate;

public class Main {

    public static void main(String[] args) {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
            OutputStandard outputStandard = new OutputStandard(context);
            OutputTamplate outputTamplate = new OutputTamplate(context);

            try {
                System.out.println("_______Standard_________");
                outputStandard.output();
                System.out.println();

            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                System.out.println("_______Template_________");
                outputTamplate.output();

            } catch (Exception e) {
                e.printStackTrace();
            }

    }
}
