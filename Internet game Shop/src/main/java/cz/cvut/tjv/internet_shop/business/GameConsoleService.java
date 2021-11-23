package cz.cvut.tjv.internet_shop.business;

import cz.cvut.tjv.internet_shop.api.dto.GameDTO;
import cz.cvut.tjv.internet_shop.dao.GameConsoleJpaRepository;
import cz.cvut.tjv.internet_shop.domain.Game;
import cz.cvut.tjv.internet_shop.domain.GameConsole;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class GameConsoleService extends AbstractCrudService<String, GameConsole, GameConsoleJpaRepository> {

    public GameConsoleService(GameConsoleJpaRepository repository) {
        super(repository);
    }

    @Override
    public boolean exists(GameConsole entity) {
        return repository.existsById(entity.getId());
    }

    public Set<String> findByGame(Game game) {
        return repository.findByGame(game);
    }

    public Set<GameConsole> getGameConsoles(GameDTO gameDTO) {
        Set<String> collection = gameDTO.getPlatformsForGame();
        Set<GameConsole> gameConsoles = new HashSet<>();
        for(var i : collection) {
            GameConsole gameConsole = readById(i).orElseThrow();
            gameConsoles.add(gameConsole);
        }
        return gameConsoles;
    }
}
