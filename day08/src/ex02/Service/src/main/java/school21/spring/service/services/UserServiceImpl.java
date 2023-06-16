package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("usersRepositoryJdbcTemplateImpl")
    private UsersRepository usersRepository;

//    @Autowired
//    public UserServiceImpl(@Qualifier("usersRepositoryJdbcTemplateImpl") UsersRepository usersRepositiry) {
//        this.usersRepository = usersRepositiry;
//    }

    @Override
    public String signUp(String email) {
        int lengthOfPassword = 8;
        String password = GeneratePassword.generateRandomPassword(lengthOfPassword);
        usersRepository.save(new User(null,email, password));
        return password;
    }
}
