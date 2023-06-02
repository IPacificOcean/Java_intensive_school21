package edu.school21.models;

import java.util.StringJoiner;

public class User {
    private String firstName;
    private String lastName;
    private int height;

    public User() {
        this.firstName = "Def. firstName";
        this.lastName = "Def. lastName";
        this.height = 0;
    }

    public User(String firstName, String lastName, int height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
    }

    private int grow(int value) {
        System.out.println("add " + value);
       return this.height += value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("firstName=" + firstName + "")
                .add("lastName=" + lastName + "")
                .add("height=" + height + "")
                .toString();
    }
}
