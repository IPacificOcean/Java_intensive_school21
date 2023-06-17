package school21.spring.service.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.TestApplicationConfig;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

public class UsersServicesImplTest {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
    private UsersRepository usersRepository =
            context.getBean("usersRepositoryJdbcTemplateImpl", UsersRepository.class);
//    private UsersRepository usersRepository =
//            context.getBean("usersRepositoryJdbcImpl", UsersRepository.class);
    private UsersService usersService = context.getBean("usersServiceImpl", UsersService.class);


    @ParameterizedTest(name = "{index} - {0} is a Email")
    @ValueSource(strings = {"someNewEmail@yandex.ru", "otherNewEmail@yandex.ru", "nextNewEmail@yandex.ru"})
    public void testReturnedPassword(String email) {
        Assertions.assertNotNull(usersService.signUp(email));
        User user = usersRepository.findByEmail(email).orElse(null);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getEmail(), email);
        usersRepository.findAll().forEach(System.out::println);
    }

}


//    Возвращает ли метод signUp случайный пароль и каждый раз сохраняет информацию в базе данных?