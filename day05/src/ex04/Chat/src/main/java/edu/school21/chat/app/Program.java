package edu.school21.chat.app;


import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Program {

    public static void createTables(DBWorker dbWorker) {
        CreateTables ct = new CreateTables(dbWorker);
        String pathSch = "src/main/resources/schema.sql";
        String pathdata = "src/main/resources/data.sql";
        ct.CreateTab(pathSch, pathdata);
    }

    public static void main(String[] args) throws SQLException {
        DBWorker dbWorker = new DBWorker();
        createTables(dbWorker);


        UsersRepositoryJdbcImpl urj = new UsersRepositoryJdbcImpl(dbWorker);
        List<User> users = urj.findAll(3, 4);
        for(User u: users) {
            System.out.println(u);
            System.out.println(); // todo use != null
        }
    }
    }

