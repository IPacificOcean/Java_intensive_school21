package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import javax.sql.DataSource;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    DataSource dataSource_;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        dataSource_ = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        return Optional.empty();
    }
}
