package edu.school21.chat.repositories;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class SecureStatements {
    DataSource connectPool_;

    public SecureStatements(DataSource connectPool) {
        connectPool_ = connectPool;
    }

    @FunctionalInterface
    public interface SQLConsumer<T> {
        void accept(T object) throws SQLException;
    }

    public void connection(SQLConsumer<? super Connection> consumer) throws SQLException {
        Objects.requireNonNull(consumer);
        try (Connection connect = connectPool_.getConnection()) {
            consumer.accept(connect);
        }
    }

    public void statement(SQLConsumer<? super Statement> consumer) throws SQLException {
        Objects.requireNonNull(consumer);
        connection((connect) -> {
            try (Statement statement = connect.createStatement()) {
                consumer.accept(statement);
            }
        });
    }

    public void preparedStatement(String sql, SQLConsumer<? super PreparedStatement> consumer)
            throws SQLException {
        Objects.requireNonNull(consumer);
        connection((connect) -> {
            try (PreparedStatement pStatement = connect.prepareStatement(sql)) {
                consumer.accept(pStatement);
            }
        });
    }


}
