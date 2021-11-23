package cz.cvut.tjv.internet_shop.api.converter;

import cz.cvut.tjv.internet_shop.api.dto.GameDTO;
import cz.cvut.tjv.internet_shop.domain.Game;
import cz.cvut.tjv.internet_shop.domain.GameConsole;
import cz.cvut.tjv.internet_shop.domain.Publisher;

import java.util.Set;

public class GameConverter {

    public static Game toModel(GameDTO gameDTO, Set<GameConsole> gameConsoles, Publisher publisher) {
        return new Game(gameDTO.getId(), gameDTO.getPrice(), publisher, gameConsoles);
    }

    public static GameDTO fromModel(Game game, Set<String> gameConsole) {
        return new GameDTO(game.getId(), game.getPrice(), game.getPublisher().getId(), gameConsole);
    }

}
