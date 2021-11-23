package cz.cvut.fit.tjv.social_network.service;

import cz.cvut.fit.tjv.social_network.business.UserService;
import cz.cvut.fit.tjv.social_network.dao.UserJpaRepository;
import cz.cvut.fit.tjv.social_network.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @InjectMocks
    UserService service;

    @Mock
    UserJpaRepository repository;

    @Test
    public void testReadAll() {
        User user1 = new User("abc");
        User user2 = new User("xyz");
        List<User> users = List.of(user1, user2);

        Mockito.when(repository.findAll()).thenReturn(users);

        Collection<User> returnedUsers = service.readAll();

        assertEquals(2, returnedUsers.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testCreate() {
        User user = new User("xyz");
        service.create(user);
        verify(repository, times(1)).save(any());
        verify(repository, times(1)).save(any(User.class));
        verify(repository, times(1)).save(user);
    }
}
