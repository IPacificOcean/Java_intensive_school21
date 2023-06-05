package edu.school21.services;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class IOData {
    public String input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        line = br.readLine();
        return line;
    }

    public void inputParamForExecMethod(Method execMethod, List<Object> valParams) throws IOException {
        long countParams = ServiceWorkers.getCountParams(execMethod);
        while (countParams != 0) {
            Class<?>[] params = execMethod.getParameterTypes();
            for (Class<?> param : params) {
                System.out.println("Enter " + param.getSimpleName() + " value");
                String argValue = input();
                ServiceWorkers.addParamsToList(valParams, param, argValue);
            }
            --countParams;
        }
    }

    public void printClassName(Set<Class<?>> setClasses) {
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
        Set<Class<?>> setClasses = ServiceWorkers.getClassesFromPackage(packageName);

        printClassName(setClasses);
        System.out.println("Enter class name:");
        String className = inputClassNameForCreatedObject(setClasses);

        Class<?> clazz = Class.forName(className);
        Field[] fields;
        Method[] methods;
        Object createdClass = ServiceWorkers.getClassInstance(clazz);
        System.out.println("___________________________");
        System.out.println("fields :");
        fields = ServiceWorkers.getFields(clazz);
        System.out.println("methods :");
        methods = ServiceWorkers.getMethods(clazz);
        System.out.println("___________________________");

        System.out.println("Let's create an object.");
        for (Field field : fields) {
            System.out.println(field.getName() + ":");
            String fieldName = input();
            ServiceWorkers.setFields(field, createdClass, fieldName);
        }
        System.out.println("Object created: " + createdClass);
        System.out.println("___________________________");

        System.out.println("Enter name of the field for changing:");
        String fieldName = input();
        String typeField = ServiceWorkers.getTypeField(fields, fieldName);
        System.out.println("Enter " + typeField + " value:");
        String fieldValueForChanged = input();
        ServiceWorkers.changedField(fields, createdClass, fieldName, fieldValueForChanged);
        System.out.println("Object updated: " + createdClass);
        System.out.println("___________________________");

        System.out.println("Enter name of the method for coll:");
        String methodName = input();
        Method execMethod = ServiceWorkers.getExecMethod(methods, methodName);
        List<Object> valParams = new ArrayList<>();
        inputParamForExecMethod(execMethod, valParams);

        Object returned = ServiceWorkers.methodInvoke(createdClass, valParams, execMethod);
        if (returned != null) {
            System.out.println("Method returned:");
            System.out.println(returned);
        }
    }
}
