package edu.school21.services;


import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class IOData {

    public Set<Class<?>>getClassesFromPackage(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return reflections.getSubTypesOf(Object.class);
    }

    public Object getClassInstance(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return clazz.getDeclaredConstructor().newInstance();
    }

    public Field[] getFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("\t " + field.getType().getSimpleName() + " " + field.getName());
        }
        return fields;
    }

    public void setFields(Field field, Object createdClass, String fieldName) throws IllegalAccessException {
        field.setAccessible(true);
        switch (field.getType().getSimpleName()) {
            case "Integer":
                field.set(createdClass, Integer.parseInt(fieldName));
                break;
            case "Double":
                field.set(createdClass, Double.parseDouble(fieldName));
                break;
            case "Boolean":
                field.set(createdClass, Boolean.parseBoolean(fieldName));
                break;
            case "Long":
                field.set(createdClass, Long.parseLong(fieldName));
                break;
            case "String":
                field.set(createdClass, fieldName);
                break;
        }
    }

    public void changedField (Field[] fields, Object createdClass, String fieldName, String fieldValueForChanged ) throws IllegalAccessException {
        Optional<Field> fieldForChanged = Arrays.stream(fields).filter(field -> field.getName().equals(fieldName)).findFirst();
        if (!fieldForChanged.isPresent()) throw new IllegalArgumentException("incorrect field name");
        Field changedField =fieldForChanged.get();
        setFields(changedField, createdClass, fieldValueForChanged);
    }

    public void getMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String parameters = Arrays.stream(method.getParameters()).map(parameter -> parameter.getType().getSimpleName()).collect(Collectors.joining(", "));
            if (method.getReturnType().getSimpleName().equals("void")) {
                System.out.println("\t " + method.getName() + "(" + parameters + ")");
            } else {
            System.out.println("\t " + method.getReturnType().getSimpleName() + " " + method.getName() + "(" + parameters + ")");
            }
        }
    }

    public String input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        long numberId = 0;
        line = br.readLine();
        return line;
    }

    public void output() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        System.out.println("Classes:");
        Set<Class<?>> setClasses = getClassesFromPackage("edu.school21.models");
        for(Class<?> clazz: setClasses) {
            System.out.println(clazz.getSimpleName());
        }
        System.out.println("Enter class name:");
        String className;
        while (true) {
            boolean status = false;
            className = input();
            for (Class<?> clazz: setClasses) {
                if (className.equals(clazz.getSimpleName())) {
                    className = clazz.getName();
                    status = true;
                    break;
                }
            }
            if (status) break;
            System.out.println("incorrect class name\nEnter correct class name:");
        }

        Class<?> clazz = Class.forName(className);
        Field[]fields;
        Object createdClass = getClassInstance(clazz);
        System.out.println("___________________________");
        System.out.println("fields :");
        fields = getFields(clazz);
        System.out.println("methods :");
        getMethods(clazz);
        System.out.println("___________________________");
        // create an object
        System.out.println("Let's create an object.");
        //set fields
        for (Field field : fields) {
            System.out.println(field.getName() + ":");
            String fieldName = input();
        setFields(field, createdClass, fieldName);
        }
        System.out.println("Object created: " + createdClass);
        System.out.println("___________________________");
        //update fields
        System.out.println("Enter name of the field for changing:");
        String fieldName = input();
        System.out.println("Enter String value:");
        String fieldValueForChanged = input();
        changedField(fields, createdClass, fieldName, fieldValueForChanged);
        System.out.println("Object updated: " + createdClass);
        System.out.println("___________________________");
        System.out.println("Enter name of the method for coll:");
    }


}
