package edu.school21.chat.app;


import edu.school21.chat.models.User;
import edu.school21.chat.repositories.CreateTables;
import edu.school21.chat.repositories.DBWorker;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import java.sql.SQLException;
import java.util.List;


public class Program {

    public static void createTables(DBWorker dbWorker) {
        CreateTables ct = new CreateTables(dbWorker);
        String pathSch = "src/main/resources/schema.sql";
        String pathdata = "src/main/resources/data.sql";
        ct.CreateTab(pathSch, pathdata);
    }

    public static void main(String[] args) {
        DBWorker dbWorker = new DBWorker();
        createTables(dbWorker);
        try {
            UsersRepositoryJdbcImpl urj = new UsersRepositoryJdbcImpl(dbWorker);
            List<User> users = urj.findAll(3, 4);
            for (User u : users) {
                System.out.println(u);
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

