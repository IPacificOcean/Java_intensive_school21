package edu.school21.services;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class ServiceWorkers {
    public static Set<Class<?>> getClassesFromPackage(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return reflections.getSubTypesOf(Object.class);
    }

    public static Object getClassInstance(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return clazz.getDeclaredConstructor().newInstance();
    }

    public static Field[] getFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("\t " + field.getType().getSimpleName() + " " + field.getName());
        }
        return fields;
    }

    public static String getTypeField(Field[] fields, String fieldName) {
        String typeField = null;
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                typeField = field.getType().getSimpleName();
            }
        }
        if (typeField == null) throw new IllegalArgumentException("incorrect field name");
        return typeField;
    }

    public static void setFields(Field field, Object createdClass, String fieldName) throws IllegalAccessException {
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

    public static void changedField(Field[] fields, Object createdClass, String fieldName, String fieldValueForChanged) throws IllegalAccessException {
        Optional<Field> fieldForChanged = Arrays.stream(fields).filter(field -> field.getName().equals(fieldName)).findFirst();
        if (!fieldForChanged.isPresent()) throw new IllegalArgumentException("incorrect field name");
        Field changedField = fieldForChanged.get();
        ServiceWorkers.setFields(changedField, createdClass, fieldValueForChanged);
    }

    public static Method[] getMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String parameters = Arrays.stream(method.getParameters()).map(parameter -> parameter.getType().getSimpleName()).collect(Collectors.joining(", "));
            System.out.println("\t " + method.getReturnType().getSimpleName() + " " + method.getName() + "(" + parameters + ")");
        }
        return methods;
    }

    public static Method getExecMethod(Method[] methods, String methodName) {
        return Arrays.stream(methods).filter((method) -> {
            String parameters = Arrays.stream(method.getParameters()).map(parameter -> parameter.getType().getSimpleName()).collect(Collectors.joining(", "));
            String methodWithParams = method.getName() + "(" + parameters + ")";
            return methodWithParams.equals(methodName);
        }).findFirst().orElseThrow(() -> {
            throw new NoSuchElementException("There is no such a method");
        });
    }

    public static Object methodInvoke(Object createdClass, List<Object> valParams, Method execMethod) throws InvocationTargetException, IllegalAccessException {
        execMethod.setAccessible(true);
        return execMethod.invoke(createdClass, valParams.toArray());
    }

    public static long getCountParams(Method methodExec) {
        return Arrays.stream(methodExec.getParameterTypes()).count();
    }

    public static void addParamsToList(List<Object> valParams, Class<?> param, String argValue) {
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
}
