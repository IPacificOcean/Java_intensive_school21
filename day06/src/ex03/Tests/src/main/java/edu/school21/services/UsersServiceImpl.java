package edu.school21.services;

import edu.school21.exception.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import javax.persistence.EntityNotFoundException;

public class UsersServiceImpl {
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    boolean authenticate(String login, String password) throws AlreadyAuthenticatedException {
        boolean status = false;

        User user;

        try {
            user = usersRepository.findByLogin(login);

        } catch (EntityNotFoundException e) {
            throw new AlreadyAuthenticatedException("a login that does not exist");
        }

        if (user.getPassword().equals(password)) {
            user.setAuthStatus(true);
            try {
                usersRepository.update(user);

            } catch (EntityNotFoundException e) {
                throw new AlreadyAuthenticatedException("was unable to update");
            }
            status = true;
        }
        return status;
    }
}
