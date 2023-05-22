package edu.school21.chat.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWorker {
    private Connection connection;

    public DBWorker() {

        try {
            String URL = "jdbc:postgresql://localhost:5432/manytomany_db";
            connection = DriverManager.getConnection(URL);

            boolean res = !connection.isClosed();
            System.out.println("__________________________" + res);
        } catch (SQLException e) {
            System.out.println("error connection");
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
