package edu.school21.chat.app;



import org.postgresql.Driver;

import java.sql.*;
//import java.sql.Driver;


public class Program {
    private static final String URL = "jdbc:postgresql://localhost:5432/manytomany_db";

    public static void main(String[] args) {

        try {

            Driver driver = new Driver();
            DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
            System.out.println("error");
        }

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement()) {
            System.out.println(connection.isClosed());
//            statement.execute("insert into book (name) values ('Pushkin')");
//           int res =  statement.executeUpdate("update book set name = 'Go' where id=2");
//            ResultSet res = statement.executeQuery("select * from book");
//            statement.addBatch("insert into book (name) values ('Ibsen')");
//            statement.addBatch("insert into book (name) values ('London')");
//            statement.addBatch("insert into book (name) values ('Akunin')");
            statement.addBatch("update book set name = 'Andersen' where id=1");
            statement.executeBatch();
            statement.clearBatch();
            boolean res = statement.isClosed();

            System.out.println(res);
        } catch (SQLException e) {
            System.out.println("error connection");
        }

        System.out.println("hello");
    }
}
