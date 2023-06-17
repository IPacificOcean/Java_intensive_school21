package school21.spring.service.services;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

@Component
@Setter
public class UsersServiceImpl implements UsersService {
    @Autowired
    @Qualifier("usersRepositoryJdbcTemplateImpl")
    private UsersRepository usersRepository;


    @Override
    public String signUp(String email) {
        int lengthOfPassword = 8;
        String password = GeneratePassword.generateRandomPassword(lengthOfPassword);
        usersRepository.save(new User(null,email, password));
        return password;
    }
}
