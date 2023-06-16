package school21.spring.service.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.IOData.OutputStandard;
import school21.spring.service.IOData.OutputTamplate;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.repositories.CreateTables;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import school21.spring.service.services.UserService;

import static school21.spring.service.services.GeneratePassword.generateRandomPassword;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        CreateTables createTablesWithHikari = context.getBean("createTables", CreateTables.class);
        createTablesWithHikari.CreateTab();


        UserService userService = context.getBean("userServiceImpl", UserService.class);
        userService.signUp("someNewEmail@yandex.ru");
        context.getBean("usersRepositoryJdbcTemplateImpl", UsersRepositoryJdbcTemplateImpl.class).findAll().forEach(System.out::println);

    }
}
