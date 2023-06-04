package edu.school21.app;

import edu.school21.services.IOData;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Program {
    public static void main(String[] args) {
        String packageName = "edu.school21.models";
        IOData ioData = new IOData();


        try {
            ioData.output(packageName);
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
