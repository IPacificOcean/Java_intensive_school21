package edu.school21.chat.app;


import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Program {

    public static void createTables(DBWorker dbWorker) {
        CreateTables ct = new CreateTables(dbWorker);
        String pathSch = "src/main/resources/schema.sql";
        String pathdata = "src/main/resources/data.sql";
        ct.CreateTab(pathSch, pathdata);
    }

    public static void main(String[] args) {
        DBWorker dbWorker = new DBWorker();
        MessagesRepository mr = new MessagesRepositoryJdbcImpl(dbWorker);
        createTables(dbWorker);
        try {
            User owner = new User(5L, "user", "user", new ArrayList<>(), new ArrayList<>());
            ChatRoom room = new ChatRoom(5L, "room", owner, new ArrayList<>());
            Message message = new Message(null, owner, room, "Hello world!", LocalDateTime.now());
            mr.save(message);
            System.out.println(message.getId());

        } catch (NotSavedSubEntityException | SQLException e) {
            e.printStackTrace();
        }
    }
    }

