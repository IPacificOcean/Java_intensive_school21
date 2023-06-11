package school21.spring.service.repositories;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateTables {
    Connection dbConnect_;
    public CreateTables(DataSource dbConnect) throws SQLException {
        dbConnect_ = dbConnect.getConnection();
    }

    public void CreateTab(String schema, String data) {
        try (Stream<String> schemaPath = Files.lines(Paths.get(schema).toAbsolutePath().normalize());
             Stream<String> dataPath = Files.lines(Paths.get(data).toAbsolutePath().normalize())
        ) {

            String schemaQwery = schemaPath.collect(Collectors.joining(System.lineSeparator()));
            String dataQwery = dataPath.collect(Collectors.joining(System.lineSeparator()));

           try (Statement statement = dbConnect_.createStatement()) {
            statement.execute(schemaQwery);
            statement.execute(dataQwery);
           } catch (SQLException e) {
               e.printStackTrace();
           }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
