package school21.spring.service.application;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.repositories.CreateTables;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

//        DataSource hikariDS = context.getBean("hicariDataSource", DataSource.class);
//        CreateTables createTablesWithHikari = new CreateTables(hikariDS);
//        createTablesWithHikari.CreateTab("src/main/resources/schema.sql", "src/main/resources/data.sql");

        DataSource managerDS = context.getBean("driverManagerDataSource", DataSource.class);
        CreateTables createTablesWithSpringManagerDS = new CreateTables(managerDS);
        createTablesWithSpringManagerDS.CreateTab("src/main/resources/schema.sql", "src/main/resources/data.sql");
    }
}
