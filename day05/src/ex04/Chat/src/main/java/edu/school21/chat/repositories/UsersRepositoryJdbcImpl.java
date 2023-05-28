package edu.school21.chat.repositories;

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
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                usersFromSpecificPage.add(
                new User(res.getLong("u_id"),
                        res.getString("u_login"),
                        res.getString("u_pass"),
                        new ArrayList<>(),
                        new ArrayList<>()));
            }
        });
//        System.out.println(findOfUsers);
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
