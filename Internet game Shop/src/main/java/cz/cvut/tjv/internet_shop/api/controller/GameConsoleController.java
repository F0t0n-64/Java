package cz.cvut.tjv.internet_shop.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.tjv.internet_shop.api.converter.GameConsoleConverter;
import cz.cvut.tjv.internet_shop.api.dto.GameConsoleDTO;
import cz.cvut.tjv.internet_shop.api.dto.GameConsoleViews;
import cz.cvut.tjv.internet_shop.api.exception.NoEntityFoundException;
import cz.cvut.tjv.internet_shop.business.EntityStateException;
import cz.cvut.tjv.internet_shop.business.GameConsoleService;
import cz.cvut.tjv.internet_shop.business.GameService;
import cz.cvut.tjv.internet_shop.domain.Game;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class GameConsoleController {

    GameConsoleService gameConsoleService;
    GameService gameService;

    public GameConsoleController(GameConsoleService gameConsoleService, GameService gameService) {
        this.gameConsoleService = gameConsoleService;
        this.gameService = gameService;
    }

    @JsonView(GameConsoleViews.Public.class)
    @GetMapping("/gameConsole")
    Collection<GameConsoleDTO> getAll() {
        return gameConsoleService.readAll().
                stream().
                map(i -> GameConsoleConverter.fromModel(i, gameService.findByConsole(i))).
                collect(Collectors.toList());
    }

    @GetMapping("/gameConsole/{id}")
    GameConsoleDTO getOne(@PathVariable String id) {
        Set<String> gamesForConsole = gameService.
                readAll().
                stream().
                filter(i -> i.getPlatformsForGame().contains(gameConsoleService.readById(id).orElseThrow())).
                map(Game::getId).
                collect(Collectors.toSet());
        return GameConsoleConverter.fromModel(gameConsoleService.readById(id).orElseThrow(NoEntityFoundException::new), gamesForConsole);
    }

    @PostMapping("/gameConsole")
    GameConsoleDTO create(@RequestBody GameConsoleDTO gameConsoleDTO) {
        gameConsoleService.create(GameConsoleConverter.toModel(gameConsoleDTO));
        return getOne(gameConsoleDTO.getId());
    }

    @PutMapping("/gameConsole/{id}")
    GameConsoleDTO update(@PathVariable String id, @RequestBody GameConsoleDTO gameConsoleDTO) {
        if(! gameConsoleDTO.getId().equals(id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "GameConsole doesn't exists");
        gameConsoleService.update(GameConsoleConverter.toModel(gameConsoleDTO));
        return getOne(gameConsoleDTO.getId());
    }

    @DeleteMapping("/gameConsole/{id}")
    void delete(@PathVariable String id) {
        if(gameConsoleService.readById(id).isEmpty())
            throw new EntityStateException();
        gameConsoleService.deleteById(id);
    }
}
