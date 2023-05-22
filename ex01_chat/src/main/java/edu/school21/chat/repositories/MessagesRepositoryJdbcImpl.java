package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    DataSource dataSource_;
    private final String msgById_ = "select * from book where id = ?";

    public MessagesRepositoryJdbcImpl(DBWorker dataSource) {
        dataSource_ = dataSource.getDS();
    }

    @Override
    public Optional<Message> findById(Long id) {
    SecureStatements pStatement = new SecureStatements(dataSource_);
        try {
            pStatement.preparedStatement(msgById_, (statement) -> {
                statement.setLong(1, id);
                ResultSet res = statement.executeQuery();
                while (res.next()) {
                    long idd = res.getInt("id");
                    String author = res.getString("name");
                    System.out.println("id = " + idd + " author: " + author);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
