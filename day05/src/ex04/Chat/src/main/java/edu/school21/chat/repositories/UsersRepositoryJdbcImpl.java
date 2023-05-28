package edu.school21.chat.repositories;

import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public List<User> findAll(int page, int size) {
        List<User> usersFromSpecificPage = new ArrayList<>();
        String findOfUsers = sqlToString("src/main/resources/allUsersQuery.sql");
        System.out.println(findOfUsers);
        return null;
    }

    public String sqlToString(String pathToSql) {
        String sql = null;
        try (Stream<String> pathToFile = Files.lines(Paths.get(pathToSql).toAbsolutePath().normalize())) {
            sql = pathToFile.collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sql;
    }
}
