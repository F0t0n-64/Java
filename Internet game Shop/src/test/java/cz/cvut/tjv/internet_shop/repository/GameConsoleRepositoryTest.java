package cz.cvut.tjv.internet_shop.repository;

import cz.cvut.tjv.internet_shop.dao.GameConsoleJpaRepository;
import cz.cvut.tjv.internet_shop.domain.GameConsole;
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
public class GameConsoleRepositoryTest {

    @Autowired
    GameConsoleJpaRepository repository;

    @Test
    public void testCreateReadDelete() {

        GameConsole xBoxOne = new GameConsole("XBoxOneTEST", 12500.00);

        repository.save(xBoxOne);

        Collection<GameConsole> gameConsoles = repository.findAll();
        Assertions.assertThat(gameConsoles).extracting(GameConsole::getId).contains("XBoxOneTEST");
        Assertions.assertThat(gameConsoles).extracting(GameConsole::getPrice).contains(12500.00);

        repository.deleteAll();
        Assertions.assertThat(repository.findAll()).isEmpty();
    }
}
