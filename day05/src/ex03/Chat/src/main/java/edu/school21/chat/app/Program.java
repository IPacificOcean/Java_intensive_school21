package edu.school21.chat.app;


import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.CreateTables;
import edu.school21.chat.repositories.DBWorker;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
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
        MessagesRepository mr = new MessagesRepositoryJdbcImpl(dbWorker);
        createTables(dbWorker);
        Optional<Message> messageOptional = mr.findById(4L);
       if (messageOptional.isPresent()) {
           Message message = messageOptional.get();
           message.setText("bay");
           message.setDateTime(null);
           mr.update(message);
       }
        System.out.println(messageOptional);
    }
    }

