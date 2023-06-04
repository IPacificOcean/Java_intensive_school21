package edu.school21.services;


import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class IOData {

    public Set<Class<?>> getClassesFromPackage(String packageName) {
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

    public void changedField(Field[] fields, Object createdClass, String fieldName, String fieldValueForChanged) throws IllegalAccessException {
        Optional<Field> fieldForChanged = Arrays.stream(fields).filter(field -> field.getName().equals(fieldName)).findFirst();
        if (!fieldForChanged.isPresent()) throw new IllegalArgumentException("incorrect field name");
        Field changedField = fieldForChanged.get();
        setFields(changedField, createdClass, fieldValueForChanged);
    }

    public Method[] getMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String parameters = Arrays.stream(method.getParameters()).map(parameter -> parameter.getType().getSimpleName()).collect(Collectors.joining(", "));
            System.out.println("\t " + method.getReturnType().getSimpleName() + " " + method.getName() + "(" + parameters + ")");
        }
        return methods;
    }


    public Method getExecMethod(Method[] methods, String methodName) {
        return Arrays.stream(methods).filter((method) -> {
            String parameters = Arrays.stream(method.getParameters()).map(parameter -> parameter.getType().getSimpleName()).collect(Collectors.joining(", "));
            String methodWithParams = method.getName() + "(" + parameters + ")";
            return methodWithParams.equals(methodName);
        }).findFirst().orElseThrow(() -> {
            throw new NoSuchElementException("There is no such a method");
        });
//        return methodExec;
    }

    public Object methodInvoke (Object createdClass, List<Object> valParams, Method execMethod) throws InvocationTargetException, IllegalAccessException {
        execMethod.setAccessible(true);
        return execMethod.invoke(createdClass, valParams.toArray());
    }

    public long getCountParams(Method methodExec) {
        return Arrays.stream(methodExec.getParameterTypes()).count();
    }

    public void addParamsToList(List<Object> valParams, Class<?> param, String argValue) {
        switch (param.getSimpleName()) {
            case "Integer":
                valParams.add(Integer.parseInt(argValue));
                break;
            case "Double":
                valParams.add(Double.parseDouble(argValue));
                break;
            case "Boolean":
                valParams.add(Boolean.parseBoolean(argValue));
                break;
            case "Long":
                valParams.add(Long.parseLong(argValue));
                break;
            case "String":
                valParams.add(argValue);
                break;
        }
    }

    public String input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        line = br.readLine();
        return line;
    }

    public void inputParamForExecMethod(Method execMethod, List<Object> valParams) throws IOException {
        long countParams = getCountParams(execMethod);
        while (countParams != 0) {
            Class<?>[] params = execMethod.getParameterTypes();
            for (Class<?> param : params) {
                System.out.println("Enter " + param.getSimpleName() + " value");
                String argValue = input();
                addParamsToList(valParams, param, argValue);
            }
            --countParams;
        }
    }

    public void printClassName (Set<Class<?>> setClasses) {
        for (Class<?> clazz : setClasses) {
            System.out.println(clazz.getSimpleName());
        }
    }

    public String inputClassNameForCreatedObject(Set<Class<?>> setClasses) throws IOException {
        String className;
        while (true) {
            boolean status = false;
            className = input();
            for (Class<?> clazz : setClasses) {
                if (className.equals(clazz.getSimpleName())) {
                    className = clazz.getName();
                    status = true;
                    break;
                }
            }
            if (status) break;
            System.out.println("incorrect class name\nEnter correct class name:");
        }
        return className;
    }

    public void output(String packageName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        System.out.println("Classes:");
        Set<Class<?>> setClasses = getClassesFromPackage(packageName);

        printClassName (setClasses);
        System.out.println("Enter class name:");
        String className = inputClassNameForCreatedObject(setClasses);

        Class<?> clazz = Class.forName(className);
        Field[] fields;
        Method[] methods;
        Object createdClass = getClassInstance(clazz);
        System.out.println("___________________________");
        System.out.println("fields :");
        fields = getFields(clazz);
        System.out.println("methods :");
        methods = getMethods(clazz);
        System.out.println("___________________________");

        System.out.println("Let's create an object.");
        for (Field field : fields) {
            System.out.println(field.getName() + ":");
            String fieldName = input();
            setFields(field, createdClass, fieldName);
        }
        System.out.println("Object created: " + createdClass);
        System.out.println("___________________________");

        System.out.println("Enter name of the field for changing:");
        String fieldName = input();
        System.out.println("Enter String value:");
        String fieldValueForChanged = input();
        changedField(fields, createdClass, fieldName, fieldValueForChanged);
        System.out.println("Object updated: " + createdClass);
        System.out.println("___________________________");

        System.out.println("Enter name of the method for coll:");
        String methodName = input();
        Method execMethod = getExecMethod(methods, methodName);
        List<Object> valParams = new ArrayList<>();
        inputParamForExecMethod(execMethod, valParams);

        Object returned = methodInvoke (createdClass, valParams, execMethod);
        if (returned != null) {
            System.out.println("Method returned:");
            System.out.println(returned);
        }
    }

}
