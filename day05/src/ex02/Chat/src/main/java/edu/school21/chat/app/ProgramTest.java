package edu.school21.chat.app;

import edu.school21.chat.models.User;
import edu.school21.chat.repositories.DBWorker;

import java.sql.*;

public class ProgramTest {
    private static final String URL = "jdbc:postgresql://localhost:5432/manytomany_db";

    public static final String INSERT_BOOK = "insert into book values(?, ?)";
    public static final String GET_ALL = "select * from book order by id desc";
    public static final String DELETE = "delete from book where id = ?";
    public static void main(String[] args) {


        try  {
//            statement.execute("insert into book (name) values ('Pushkin')");
//           int res =  statement.executeUpdate("update book set name = 'Go' where id=2");
//            ResultSet res = statement.executeQuery("select * from book");
//            statement.addBatch("insert into book (name) values ('Ibsen')");
//            statement.addBatch("insert into book (name) values ('London')");
//            statement.addBatch("insert into book (name) values ('Akunin')");
//            statement.addBatch("update book set name = 'Pero' where id=2");
//            statement.executeBatch();
//            statement.clearBatch();

            DBWorker dbWorker = new DBWorker();
//            Statement statement = dbWorker.getConnection().createStatement();
//            ResultSet resU
//                    = statement.executeQuery("select * from users");
//            while (resU.next()) {
//                User user = new User(resU.getLong("id"), resU.getString("login"),  resU.getString("password"), null, null);
//
//                System.out.println(user.toString());
//            }
//            statement.close();
//            boolean res = statement.isClosed();
//
//            System.out.println(res);

            PreparedStatement pStatement = dbWorker.getConnection().prepareStatement(INSERT_BOOK);
//            pStatement.setInt(1, 8); // INSERT_BOOK
//            pStatement.setString(2, "Leskov");
//            pStatement.execute();
//            ResultSet res = pStatement.executeQuery(); // GET_ALL
//            while (res.next()) {
//                int id = res.getInt("id");
//                String author = res.getString("name");
//                System.out.println("id = " + id + " author: " + author);
//            }
//            pStatement.setInt(1, 8); // DELETE
//            pStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("error connection");
        }

        System.out.println("hello1");
    }
}
