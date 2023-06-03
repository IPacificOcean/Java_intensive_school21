package edu.school21.services;


import edu.school21.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class IOData {
       private Class<?> clUser;
       private Class<?> clCar;
//       private Object user;
//       private Object car;
private List<String[]> listClasses;
    private String nameOfUsersClass;
    private String nameOfCarsClass;

    private String simpleNameOfUsersClass;
    private String simpleNOfCarsClass;

    public IOData() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        clUser = Class.forName("edu.school21.models.User");
        clCar = Class.forName("edu.school21.models.Car");
//        user = clUser.getDeclaredConstructor().newInstance();
//        car = clCar.getDeclaredConstructor().newInstance();
        nameOfUsersClass = clUser.getName();
        nameOfCarsClass = clCar.getName();
        simpleNameOfUsersClass = clUser.getSimpleName();
        simpleNOfCarsClass = clCar.getSimpleName();
        listClasses = new ArrayList<>();
    }

    public void getListClassesNames() {
        listClasses.add(new String[]{simpleNameOfUsersClass, nameOfUsersClass});
        listClasses.add(new String[]{simpleNOfCarsClass, nameOfCarsClass});
    }

    public void getFields(String className) throws ClassNotFoundException {
       Class<?> clazz = Class.forName(className);
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("     " + field.getType().getSimpleName() + " " + field.getName());

        }
    }

    public void getMethods(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getName());
            }
                System.out.println("     " + method.getReturnType().getSimpleName() + " " + method.getName());
        }

    }

    public String input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        long numberId = 0;
        System.out.println("Enter class name:");
        line = br.readLine();
        return line;
    }

    public void output() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        getListClassesNames();
        System.out.println("Classes:");
        System.out.println(nameOfUsersClass);
        System.out.println(nameOfCarsClass);
        String className;
        while (true) {
            boolean status = false;
            className = input();
            for (String[] name : listClasses) {
                if (className.equals(name[0])) {
                    className = name[1];
                    status = true;
                    break;
                }
            }
            if (status) break;
            System.out.println("incorrect class name");
        }

        System.out.println(className);
        System.out.println("___________________________");
        System.out.println("fields :");
        getFields(className);
        System.out.println("methods :");
        getMethods(className);
        System.out.println("___________________________");
    }


}
