package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBWorker {
    String configFile = "src/main/resources/db.properties";
    HikariConfig cfg;
    HikariDataSource ds;

    public DBWorker() {
        cfg = new HikariConfig(configFile);
        ds = new HikariDataSource(cfg);
    }

    public HikariDataSource getDS() {
        return ds;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
