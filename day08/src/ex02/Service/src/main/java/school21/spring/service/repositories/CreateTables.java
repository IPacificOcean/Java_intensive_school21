package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Component
@PropertySource("classpath:db.properties")
public class CreateTables {
    @Value("${db.schema}")
    String schema;

    @Value("${db.data}")
    String data;
    @Autowired
    @Qualifier("hikariDataSource")
    private DataSource dataSource;


    public void createTab() {
        try (Stream<String> schemaPath = Files.lines(Paths.get(schema).toAbsolutePath().normalize());
             Stream<String> dataPath = Files.lines(Paths.get(data).toAbsolutePath().normalize())
        ) {

            String schemaQwery = schemaPath.collect(Collectors.joining(System.lineSeparator()));
            String dataQwery = dataPath.collect(Collectors.joining(System.lineSeparator()));

            try (Statement statement = dataSource.getConnection().createStatement()) {
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
