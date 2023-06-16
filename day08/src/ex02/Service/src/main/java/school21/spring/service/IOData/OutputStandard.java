package school21.spring.service.IOData;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.CreateTables;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OutputStandard {
    AnnotationConfigApplicationContext context;

    public OutputStandard(AnnotationConfigApplicationContext context) {
        this.context = context;
    }

    public void output() throws SQLException {

//        DataSource hikariDS = context.getBean("hikariDataSource", DataSource.class);
        CreateTables createTablesWithHikari = context.getBean("createTables", CreateTables.class);
        createTablesWithHikari.CreateTab();


    }
}
