package cz.cvut.fit.tjv.social_network.repository;

import cz.cvut.fit.tjv.social_network.dao.UserJpaRepository;
import cz.cvut.fit.tjv.social_network.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserJpaRepository repository;

    @Test
    public void testCreateReadDelete() {
        User user = new User("abc");

        repository.save(user);

        Collection<User> users = repository.findAll();
        Assertions.assertThat(users).extracting(User::getUsername).containsOnly("abc");

        repository.deleteAll();
        Assertions.assertThat(repository.findAll()).isEmpty();
    }
}
