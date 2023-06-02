package edu.school21.app;

import edu.school21.models.Car;
import edu.school21.models.User;

import java.lang.reflect.*;

public class Program_test {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException {
        User user = new User();
        Field[] fs = User.class.getDeclaredFields();
        Method[] ms = User.class.getDeclaredMethods();
        Constructor[] constructors = User.class.getConstructors();

        for (Constructor constructor : constructors) {
            Parameter[] parameters = constructor.getParameters();

            for (Parameter parameter : parameters) {
                System.out.printf(parameter.getType().getSimpleName() + "\n");
            }
        }

        for (Field field : fs) {
            System.out.printf(field.getName() + "\n");
            field.setAccessible(true);
            if (field.getType().getSimpleName().equals("int")) {
            System.out.println(field.getInt(user));
            }
        }

        for (Method method : ms) {

            System.out.printf(Modifier.toString(method.getModifiers()) + "\n");
            System.out.printf(method.getModifiers() + "\n");
            if (method.getName().equals("grow")) {
                method.setAccessible(true);
                method.invoke(user, 10);
            } else {
                System.out.println(method.invoke(user));
            }
        }

//        Class<?> clazz = Class.forName("edu.school21.models.Car");
        Class<?> clazz = Car.class;
//        Object obj = clazz.getDeclaredConstructor().newInstance();
        Object obj = clazz.newInstance();
//        Method method = clazz.getDeclaredMethod("toString");
        Method method = clazz.getDeclaredMethod("refuelCar", int.class);
        System.out.println(method.invoke(obj, 10));
    }
}
