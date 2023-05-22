package edu.school21.chat.app;


import edu.school21.chat.repositories.DBWorker;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.SecureStatements;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Program {

    public static final String INSERT_BOOK = "insert into book values(?, ?)";
    public static final String GET_ALL = "select * from book order by id desc";
    public static final String DELETE = "delete from book where id = ?";
    public static void main(String[] args) throws SQLException {
        DBWorker dbWorker = new DBWorker();

        MessagesRepository mr = new MessagesRepositoryJdbcImpl(dbWorker);
        SecureStatements secureStatements = new SecureStatements(dbWorker.getDS());
        mr.findById(3L);

//        secureStatements.preparedStatement(GET_ALL, (statement) -> {
////                    statement.setInt(1, 8);
////                    statement.setString(2, "Leskov");
////                    statement.execute();
//
////                    statement.setInt(1, 8);
////                    statement.executeUpdate();
//
//                    ResultSet res = statement.executeQuery();
//                    while (res.next()) {
//                        int id = res.getInt("id");
//                        String author = res.getString("name");
//                        System.out.println("id = " + id + " author: " + author);
//                    }
//                }
//        );

        System.out.println("__________________________bay");
    }
}
