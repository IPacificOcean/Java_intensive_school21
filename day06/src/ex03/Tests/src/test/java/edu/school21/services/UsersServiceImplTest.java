package edu.school21.services;

import edu.school21.exception.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
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
    public void correctWorksShouldReturnTrue() throws AlreadyAuthenticatedException {
        String login = "alex@mail.com";
        String password = "alex";
        Mockito.when(usersRepository.findByLogin(login)).thenReturn(user);
        boolean status = usersServiceImpl.authenticate(login, password);
        Assertions.assertTrue(status);
    }

    @Test
    public void shouldReturnTrow() {
        String login = "alex@mail.com";
        String password = "alex";
        Mockito.doThrow(new EntityNotFoundException()).when(usersRepository).update(user);
        Assertions.assertThrows(AlreadyAuthenticatedException.class, () -> usersServiceImpl.authenticate(login, password));
    }

    @Test
    public void incorrectLoginShouldReturnFalse() {
        String login = "justas@mail.com";
        String password = "alex";
        Mockito.when(usersRepository.findByLogin(login)).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(AlreadyAuthenticatedException.class, () -> usersServiceImpl.authenticate(login, password));
    }

    @Test
    public void incorrectPasswordShouldReturnFalse() throws AlreadyAuthenticatedException {
        String login = "alex@mail.com";
        String password = "justas";
        Mockito.when(usersRepository.findByLogin(login)).thenReturn(user);
        boolean status = usersServiceImpl.authenticate(login, password);
        Assertions.assertFalse(status);
    }
}
