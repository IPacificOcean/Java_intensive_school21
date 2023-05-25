package edu.school21.chat.repositories;

import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
            String msgById_ = "select * from chat.message where id = ?";
            return pStatement.preparedStatement(msgById_, (statement) -> {
                statement.setLong(1, id);
                ResultSet res = statement.executeQuery();
                if (!res.next()) {
                    return Optional.empty();
                }
                Long authorId = res.getLong("author");
                Long roomId = res.getLong("room");
                String text = res.getString("text");
                LocalDateTime dateTime = res.getTimestamp("date_time").toLocalDateTime();

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
        String userQuery = "select * from chat.users where id = ?";
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
            String sql = "select * from chat.chatroom where id = ?";
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

@Override
public void save(Message message) throws SQLException {
    String messageStatement = "insert into chat.message (author, room, text, date_time) values (?,?,?,?)";
    checkBeforeSave(message);

    pStatement.preparedStatement(messageStatement, (statement) -> {
        statement.setLong(1, message.getAuthor().getId());
        statement.setLong(2, message.getRoom().getId());
        statement.setString(3, message.getText());
        statement.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));

        int checkUpdate = statement.executeUpdate();
        if (checkUpdate != 0) {
            ResultSet res = statement.getGeneratedKeys();
            if (res.next()) {
                long id = res.getLong(1);
                message.setId(id);
            } else {
                throw new NotSavedSubEntityException("id does not exist");
            }
        }
    });
}
    void checkBeforeSave(Message message) {
        if (message.getAuthor() == null) {
            throw new NotSavedSubEntityException("Author not defined");
        }
        if (message.getRoom() == null) {
            throw new NotSavedSubEntityException("ChatRoom not defined");
        }

        if (!findUserById(message.getAuthor().getId()).isPresent()) {
            throw new NotSavedSubEntityException("User not found");
        }
        if (!findChatRoomById(message.getRoom().getId()).isPresent()) {
            throw new NotSavedSubEntityException("ChatRoom not found");
        }
    }

    @Override
    public void update(Message message) throws SQLException {
        String messageStatement = "update chat.message set (author, room , text, date_time) = (?, ?, ?, ?) where id = ?";
//        String messageStatement = "update message set author = ?, room = ?, text = ?, date_time = ? where id = ?";
        checkBeforeSave(message);

        if (message.getText() == null) {
            message.setText("");
        }

        if (message.getDateTime() == null) {
            message.setDateTime(LocalDateTime.now());
        }

        pStatement.preparedStatement(messageStatement, (statement) -> {
            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getRoom().getId());
            statement.setString(3, message.getText());
            statement.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));
            statement.setLong(5, message.getId());

            int checkUpdate = statement.executeUpdate();
            if (checkUpdate == 0) {
                    throw new NotSavedSubEntityException("Update error");
                }
        });
    }
}
