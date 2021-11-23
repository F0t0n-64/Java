package cz.cvut.tjv.internet_shop.api.converter;

import cz.cvut.tjv.internet_shop.api.dto.GameConsoleDTO;
import cz.cvut.tjv.internet_shop.domain.GameConsole;

import java.util.Set;

public class GameConsoleConverter {

    public static GameConsole toModel(GameConsoleDTO gameConsole) {
        return new GameConsole(gameConsole.getId(), gameConsole.getPrice());
    }

    public static GameConsoleDTO fromModel(GameConsole gameConsole, Set<String> gamesForConsole) {
        return new GameConsoleDTO(gameConsole.getId(), gameConsole.getPrice(), gamesForConsole);
    }
}
