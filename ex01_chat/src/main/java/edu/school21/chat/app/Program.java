package edu.school21.chat.app;


import edu.school21.chat.repositories.DBWorker;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Program {

    public static final String INSERT_BOOK = "insert into book values(?, ?)";
    public static final String GET_ALL = "select * from book order by id desc";
    public static final String DELETE = "delete from book where id = ?";
    public static void main(String[] args) {
        DBWorker dbWorker = new DBWorker();

        MessagesRepository mr = new MessagesRepositoryJdbcImpl(dbWorker.getDS());

        try (PreparedStatement pStatement = dbWorker.getConnection().prepareStatement(DELETE);){
//            pStatement.setInt(1, 8);
//            pStatement.setString(2, "Leskov");
//            pStatement.execute();
//            ResultSet res = pStatement.executeQuery();
//
//            while (res.next()) {
//                int id = res.getInt("id");
//                String author = res.getString("name");
//                System.out.println("id = " + id + " author: " + author);
//            }

            pStatement.setInt(1, 8);
            pStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println("error connection in program");
        }
        System.out.println("__________________________bay");
    }
}
