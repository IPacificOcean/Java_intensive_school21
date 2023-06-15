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

        DataSource managerDS = context.getBean("springManagerDataSource", DataSource.class);
        CreateTables createTablesWithSpringManagerDS = context.getBean("createTables", CreateTables.class);
        createTablesWithSpringManagerDS.CreateTab();

//        UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTempl = context.getBean("uRepJdbcTemplImpl", UsersRepositoryJdbcTemplateImpl.class);
//
//        System.out.println("findById(1L):");
//        User user = usersRepositoryJdbcTempl.findById(1L);
//        System.out.println(user + "\n");
//
//        System.out.println("findAll():");
//        List<User> userList = usersRepositoryJdbcTempl.findAll();
//        userList.forEach(System.out::println);
//        System.out.println();
//
//        System.out.println("save():");
//        User userForSave = new User();
//        userForSave.setEmail("someEmailNew@save.com");
//        usersRepositoryJdbcTempl.save(userForSave);
//        System.out.println(userForSave);
//        System.out.println();
//
//        System.out.println("update(2, \"update@ya.ru\"):");
//        User userForUpdate = new User(2, "update@ya.ru");
//        usersRepositoryJdbcTempl.update(userForUpdate);
//        user = usersRepositoryJdbcTempl.findById(2L);
//        System.out.println(user + "\n");
//
//        usersRepositoryJdbcTempl.delete(1L);
//
//        System.out.println("findByEmail(\"someEmail3@yandex.ru\"):");
//        Optional<User> userFindByEmail = usersRepositoryJdbcTempl.findByEmail("someEmail3@yandex.ru");
//        System.out.println(userFindByEmail.get());

    }

}
