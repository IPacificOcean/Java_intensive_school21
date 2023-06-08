package edu.school21.repositories;

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

    @FunctionalInterface
    public interface SQLFunction<T, R> {
        R apply(T object) throws SQLException;
    }

    public void connection(SQLConsumer<? super Connection> consumer) throws SQLException {
        Objects.requireNonNull(consumer);
        try (Connection connect = connectPool_.getConnection()) {
            consumer.accept(connect);
        }
    }

    public <R> R connection(SQLFunction<? super Connection, ? extends R> function)
            throws SQLException {
        Objects.requireNonNull(function);
        try (Connection connect = connectPool_.getConnection()) {
            return function.apply(connect);
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

    public <R> R statement(SQLFunction<? super Statement, ? extends R> function) throws SQLException {
        Objects.requireNonNull(function);
        return connection((connect) -> {
            try (Statement statement = connect.createStatement()) {
                return function.apply(statement);
            }
        });
    }

    public void preparedStatement(String sql, SQLConsumer<? super PreparedStatement> consumer)
            throws SQLException {
        Objects.requireNonNull(consumer);
        connection((connect) -> {
            try (PreparedStatement pStatement = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                consumer.accept(pStatement);
            }
        });
    }

    public <R> R preparedStatement(String sql,
                                   SQLFunction<? super PreparedStatement, ? extends R> function) throws SQLException {
        Objects.requireNonNull(function);
        return connection((connect) -> {
            try (PreparedStatement pStatement = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                return function.apply(pStatement);
            }
        });
    }




}
