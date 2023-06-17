package school21.spring.service.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.repositories.CreateTables;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UsersService;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        CreateTables createTablesWithHikari = context.getBean("createTables", CreateTables.class);
        createTablesWithHikari.CreateTab();


        UsersService userService = context.getBean("usersServiceImpl", UsersService.class);
        userService.signUp("someNewEmail@yandex.ru");
        context.getBean("usersRepositoryJdbcTemplateImpl", UsersRepositoryJdbcTemplateImpl.class).findAll().forEach(System.out::println);

    }
}
