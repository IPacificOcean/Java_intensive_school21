package edu.school21.services;

import edu.school21.exception.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UsersServiceImplTest {
    @Mock
    private UsersRepository usersRepository;
    @InjectMocks
    private UsersServiceImpl usersServiceImpl;

    private final User user;

    {
        user = new User(1L, "alex@mail.com", "alex", false);
    }


    @Test
    public void shouldReturnTrue() throws AlreadyAuthenticatedException {
        String login = "alex@mail.com";
        String password = "alex";
        Mockito.when(usersRepository.findByLogin(login)).thenReturn(user);
        boolean status = usersServiceImpl.authenticate(login, password);
        Mockito.verify(usersRepository, Mockito.times(1)).findByLogin(login);
        Mockito.verify(usersRepository, Mockito.times(1)).update(Mockito.any(User.class));
        Assertions.assertTrue(status);
    }

    @Test
    public void incorrectLoginShouldReturnFalse() {
        String login = "justas@mail.com";
        String password = "alex";
        Mockito.when(usersRepository.findByLogin(login)).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(AlreadyAuthenticatedException.class, () -> usersServiceImpl.authenticate(login, password));
        Mockito.verify(usersRepository, Mockito.times(1)).findByLogin(Mockito.anyString());
        Mockito.verify(usersRepository, Mockito.never()).update(Mockito.any(User.class));
    }

    @Test
    public void incorrectPasswordShouldReturnFalse() throws AlreadyAuthenticatedException {
        String login = "alex@mail.com";
        String password = "justas";
        Mockito.when(usersRepository.findByLogin(login)).thenReturn(user);
        boolean status = usersServiceImpl.authenticate(login, password);
        Mockito.verify(usersRepository, Mockito.times(1)).findByLogin(login);
        Mockito.verify(usersRepository, Mockito.never()).update(Mockito.any(User.class));
        Assertions.assertFalse(status);
    }

}
