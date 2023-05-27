package edu.school21.chat.repositories;

import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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
        String findOfUsers = "";
        return null;
    }
}
