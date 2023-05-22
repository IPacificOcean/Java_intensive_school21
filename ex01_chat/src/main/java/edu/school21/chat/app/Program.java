package edu.school21.chat.app;


import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Program {
    public static void main(String[] args) {

        try {
            DBWorker dbWorker = new DBWorker();
            Statement statement = dbWorker.getConnection().createStatement();
            ResultSet resU
                    = statement.executeQuery("select * from users");
            while (resU.next()) {
                User user = new User(resU.getLong("id"), resU.getString("login"),  resU.getString("password"));

                System.out.println(user.toString());
            }

//            ResultSet resM = statement.executeQuery("select * from users");
//            while (resM.next()) {
//                Message message = new Message(resM.getLong("id"), resM.getLong("author"),  resM.getLong("room"),
//                resM.getString("text"), resM.getTime("date_time"));
//
//                System.out.println(message.toString());
//            }
        } catch (SQLException e) {
            System.out.println("error connection in program");
        }
        System.out.println("__________________________bay");
    }
}
