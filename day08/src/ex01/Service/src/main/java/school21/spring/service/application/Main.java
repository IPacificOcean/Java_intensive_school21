package school21.spring.service.application;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.CreateTables;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

//        DataSource hikariDS = context.getBean("hicariDataSource", DataSource.class);
//        CreateTables createTablesWithHikari = new CreateTables(hikariDS);
//        createTablesWithHikari.CreateTab("src/main/resources/schema.sql", "src/main/resources/data.sql");

        DataSource managerDS = context.getBean("driverManagerDataSource", DataSource.class);
        CreateTables createTablesWithSpringManagerDS = new CreateTables(managerDS);
        createTablesWithSpringManagerDS.CreateTab("src/main/resources/schema.sql", "src/main/resources/data.sql");

        UsersRepositoryJdbcImpl usersRepositoryJdbc = context.getBean("uRepJdbcImpl", UsersRepositoryJdbcImpl.class);
        User user = usersRepositoryJdbc.findById(1L);
        List<User> userList = usersRepositoryJdbc.findAll();
        System.out.println(user + "\n");
        userList.forEach(System.out::println);
        System.out.println();
        User userForSave = new User();
        userForSave.setEmail("someEmailNew@save.com");
        usersRepositoryJdbc.save(userForSave);
        System.out.println(userForSave);
        System.out.println();
        User userForUpdate = new User(2, "update@ya.ru");
        usersRepositoryJdbc.update(userForUpdate);
        usersRepositoryJdbc.delete(1L);
        Optional<User> userFindByEmail = usersRepositoryJdbc.findByEmail("someEmail4@yandex.ru");
        System.out.println(userFindByEmail.get());
    }
}
