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

        DataSource hikariDS = context.getBean("hikariDataSource", DataSource.class);
        CreateTables createTablesWithHikari = context.getBean("createTables", CreateTables.class);
        createTablesWithHikari.CreateTab();

//        UsersRepositoryJdbcImpl usersRepositoryJdbc = context.getBean("uRepJdbcImpl", UsersRepositoryJdbcImpl.class);
//
//        System.out.println("findById(1L):");
//        User user = usersRepositoryJdbc.findById(1L);
//        System.out.println(user + "\n");
//
//        List<User> userList = usersRepositoryJdbc.findAll();
//        System.out.println("findAll():");
//        userList.forEach(System.out::println);
//        System.out.println();
//
//        System.out.println("save():");
//        User userForSave = new User();
//        userForSave.setEmail("someEmailNew@save.com");
//        usersRepositoryJdbc.save(userForSave);
//        System.out.println(userForSave);
//        System.out.println();
//
//        System.out.println("update(2, \"update@ya.ru\"):");
//        User userForUpdate = new User(2, "update@ya.ru");
//        usersRepositoryJdbc.update(userForUpdate);
//        user = usersRepositoryJdbc.findById(2L);
//        System.out.println(user + "\n");
//
//        usersRepositoryJdbc.delete(1L);
//
//        System.out.println("findByEmail(\"someEmail4@yandex.ru\"):");
//        Optional<User> userFindByEmail = usersRepositoryJdbc.findByEmail("someEmail4@yandex.ru");
//        System.out.println(userFindByEmail.get());
    }
}
