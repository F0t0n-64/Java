package cz.cvut.tjv.internet_shop.repository;

import cz.cvut.tjv.internet_shop.dao.GameJpaRepository;
import cz.cvut.tjv.internet_shop.domain.Game;
import cz.cvut.tjv.internet_shop.domain.Publisher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Collection;
import java.util.HashSet;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GameRepositoryTest {

    @Autowired
    GameJpaRepository repository;

    @Test
    public void testCreateReadDelete() {

        Publisher publisher = new Publisher("Sony", 9999999);
        Game game = new Game("Dark souls", 800.00, publisher, new HashSet<>());

        repository.save(game);

        Collection<Game> games = repository.findAll();
        Assertions.assertThat(games).extracting(Game::getId).contains("Dark souls");
        Assertions.assertThat(games).extracting(Game::getPrice).contains(800.00);

        repository.deleteAll();
        Assertions.assertThat(repository.findAll()).isEmpty();
    }
}
