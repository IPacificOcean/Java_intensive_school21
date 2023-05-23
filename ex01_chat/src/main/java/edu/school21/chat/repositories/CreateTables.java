package edu.school21.chat.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateTables {
    SecureStatements dbConnect_;
    public CreateTables(DBWorker dbConnect) {
        dbConnect_ = new SecureStatements(dbConnect.getDS());
    }
    public void CreateTab(String schema, String data) {
        try (Stream schemaPath = Files.lines(Paths.get(schema).toAbsolutePath().normalize());
             Stream dataPath = Files.lines(Paths.get(data).toAbsolutePath().normalize())
        ) {

            String schemaQwery = schemaPath.collect(Collectors.joining(System.lineSeparator())).toString();
            String dataQwery  = dataPath.collect(Collectors.joining(System.lineSeparator())).toString();

            dbConnect_.statement((statement) -> {
                statement.execute(schemaQwery);
                statement.execute(dataQwery);
            });
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
