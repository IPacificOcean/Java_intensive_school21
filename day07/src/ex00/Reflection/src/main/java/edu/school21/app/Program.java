package edu.school21.app;

import edu.school21.services.IOData;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Program {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        IOData ioData = new IOData();
        try {
            ioData.output();
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
