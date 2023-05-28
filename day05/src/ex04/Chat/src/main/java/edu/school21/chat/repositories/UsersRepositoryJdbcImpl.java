package edu.school21.chat.repositories;

import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    private DataSource dataSource_;

    private SecureStatements pStatement_;

    public UsersRepositoryJdbcImpl(DBWorker dataSource) {
        dataSource_ = dataSource.getDS();
        pStatement_ = new SecureStatements(dataSource_);
    }

    @Override
    public List<User> findAll(int page, int size) throws SQLException {
        List<User> usersFromSpecificPage = new ArrayList<>();
        String findOfUsers = sqlToString("src/main/resources/allUsersQuery.sql");
        pStatement_.preparedStatement(findOfUsers, (statement) -> {
            statement.setInt(1, page*size-1);
            statement.setInt(2, size);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                User user = new User(
                        res.getLong("u_id"),
                        res.getString("u_login"),
                        res.getString("u_pass"),
                        new ArrayList<>(),
                        new ArrayList<>());
                if (!usersFromSpecificPage.contains(user)) {
                    usersFromSpecificPage.add(user);
                }

                user = usersFromSpecificPage.get(usersFromSpecificPage.size() - 1);
                long owner_chat_id = res.getLong("owner_chat_id");
                if (owner_chat_id != 0 &&
                        user.getCreatedRooms().stream().noneMatch(chatRoom -> chatRoom.getId() == owner_chat_id)) {
                    ChatRoom ownerChat = new ChatRoom(
                            res.getLong("owner_chat_id"),
                            res.getString("owner_chat_name"),
                            null,
                            null);
                    user.getCreatedRooms().add(ownerChat);
                }

                long socializes_chat_id = res.getLong("socializes_chat_id");
                if (socializes_chat_id != 0 &&
                        user.getSocializingRooms().stream().noneMatch(chatRoom -> chatRoom.getId() == socializes_chat_id)) {
                    ChatRoom socializingChat = new ChatRoom(
                            res.getLong("socializes_chat_id"),
                            res.getString("socializes_chat_name"),
                            null,
                            null);
                    user.getSocializingRooms().add(socializingChat);
                }
            }
            return usersFromSpecificPage;
        });
        return usersFromSpecificPage;
    }

    private String sqlToString(String pathToSql) {
        String sql = null;
        try (Stream<String> pathToFile = Files.lines(Paths.get(pathToSql).toAbsolutePath().normalize())) {
            sql = pathToFile.collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sql;
    }
}
