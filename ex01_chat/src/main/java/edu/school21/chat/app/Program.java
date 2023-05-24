package edu.school21.chat.app;


import edu.school21.chat.repositories.*;

import java.io.IOException;


public class Program {

    public static void createTables(DBWorker dbWorker) {
        CreateTables ct = new CreateTables(dbWorker);
        String pathSch = "src/main/resources/schema.sql";
        String pathdata = "src/main/resources/data.sql";
        ct.CreateTab(pathSch, pathdata);
    }

    public static void main(String[] args) throws IOException {
        DBWorker dbWorker = new DBWorker();
        MessagesRepository mr = new MessagesRepositoryJdbcImpl(dbWorker);
        createTables(dbWorker);
        IOData io = new IOData();
        long numberId = io.input();
        io.output(numberId, mr);
    }
}
