package edu.school21.chat.repositories;

import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    DataSource dataSource_;
    SecureStatements pStatement;

    public MessagesRepositoryJdbcImpl(DBWorker dataSource) {
        dataSource_ = dataSource.getDS();
        pStatement = new SecureStatements(dataSource_);
    }

    @Override
    public Optional<Message> findById(Long id) {
        try {
            String msgById_ = "select * from message where id = ?";
            return pStatement.preparedStatement(msgById_, (statement) -> {
                statement.setLong(1, id);
                ResultSet res = statement.executeQuery();
                if (!res.next()) {
                    return Optional.empty();
                }
                Long authorId = res.getLong("author");
                Long roomId = res.getLong("room");
                String text = res.getString("text");
                Timestamp dateTime = res.getTimestamp("date_time");

                User author = findUserById(authorId).orElse(null);
                ChatRoom room = findChatRoomById(roomId).orElse(null);

                return Optional.of(new Message(id, author, room, text, dateTime));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<User> findUserById(Long id) {
        String userQuery = "SELECT * FROM users WHERE id = ?";
        try {
            return pStatement.preparedStatement(userQuery, (statement) -> {
                statement.setLong(1, id);

                ResultSet res = statement.executeQuery();

                if (!res.next()) {
                    return Optional.empty();
                }
                String login = res.getString("login");
                String password = res.getString("password");

                return Optional.of(new User(id, login, password, null, null));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<ChatRoom> findChatRoomById(Long id) {
        try {
            String sql = "select * from chatroom where id = ?";
            return pStatement.preparedStatement(sql, (stmt) -> {
                stmt.setLong(1, id);
                ResultSet res = stmt.executeQuery();
                if (!res.next()) {
                    return Optional.empty();
                }
                String name = res.getString("name");
                return Optional.of(new ChatRoom(id, name, null, null));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
