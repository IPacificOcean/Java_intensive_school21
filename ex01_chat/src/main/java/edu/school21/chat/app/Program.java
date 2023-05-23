package edu.school21.chat.app;


import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.DBWorker;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Program {

    public static long input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        long numberId = 0;
        System.out.println("Enter a message id:");
        while ((line = br.readLine()) != null) {
            try {
                numberId = Long.parseLong(line);
                return numberId;
            } catch (NumberFormatException e) {
                System.out.println("Enter a number:");
            }
        }
        return numberId;
    }

    public static void main(String[] args) throws IOException {
        DBWorker dbWorker = new DBWorker();
        MessagesRepository mr = new MessagesRepositoryJdbcImpl(dbWorker);
        long numberId = input();
        Message message = mr.findById(numberId).orElse(null);
        if (message == null) {
            System.out.println("Message with id = " + numberId + " does not exist");
        } else {
        System.out.println(message);
        }
    }
}
