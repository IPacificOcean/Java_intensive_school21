package school21.spring.service.IOData;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.CreateTables;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OutputTamplate {
    AnnotationConfigApplicationContext context;

    public OutputTamplate(AnnotationConfigApplicationContext context) {
        this.context = context;
    }

    public void output () throws SQLException {

//        DataSource managerDS = context.getBean("springManagerDataSource", DataSource.class);
        CreateTables createTablesWithSpringManagerDS = context.getBean("createTables", CreateTables.class);
        createTablesWithSpringManagerDS.CreateTab();

    }

}
