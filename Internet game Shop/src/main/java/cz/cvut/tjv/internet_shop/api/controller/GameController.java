package cz.cvut.tjv.internet_shop.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.tjv.internet_shop.api.converter.GameConverter;
import cz.cvut.tjv.internet_shop.api.dto.GameDTO;
import cz.cvut.tjv.internet_shop.api.dto.GameViews;
import cz.cvut.tjv.internet_shop.api.exception.NoEntityFoundException;
import cz.cvut.tjv.internet_shop.business.EntityStateException;
import cz.cvut.tjv.internet_shop.business.GameConsoleService;
import cz.cvut.tjv.internet_shop.business.GameService;
import cz.cvut.tjv.internet_shop.business.PublisherService;
import cz.cvut.tjv.internet_shop.domain.GameConsole;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class GameController {

    GameService gameService;
    PublisherService publisherService;
    GameConsoleService gameConsoleService;

    public GameController(GameService gameService, PublisherService publisherService, GameConsoleService gameConsoleService) {
        this.gameService = gameService;
        this.publisherService = publisherService;
        this.gameConsoleService = gameConsoleService;
    }

    @JsonView(GameViews.Public.class)
    @GetMapping("/games")
    Collection<GameDTO> getAll() {
        return gameService.readAll().
                stream().
                map(i -> GameConverter.fromModel(i, gameConsoleService.findByGame(i))).
                collect(Collectors.toList());
    }

    @GetMapping("/games/{id}")
    GameDTO getOne(@PathVariable String id) {
        Set<String> gamesConsole = gameConsoleService.
                readAll().
                stream().
                filter(i -> i.getGamesForConsole().contains(gameService.readById(id).orElseThrow())).
                map(GameConsole::getId).
                collect(Collectors.toSet());
        return GameConverter.fromModel(gameService.readById(id).orElseThrow(NoEntityFoundException::new), gamesConsole);
    }

    @PostMapping("/games")
    GameDTO create(@RequestBody GameDTO gameDTO) {
        gameService.create(
                GameConverter.toModel(gameDTO,
                        gameConsoleService.getGameConsoles(gameDTO),
                        publisherService.readById(gameDTO.getPublisher()).orElseThrow()));
        return getOne(gameDTO.getId());
    }

    @PutMapping("/games/{id}")
    GameDTO update(@PathVariable String id, @RequestBody GameDTO gameDTO) {
        if(! gameDTO.getId().equals(id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Game doesn't exists");
        gameService.update(
                GameConverter.toModel(gameDTO,
                        gameConsoleService.getGameConsoles(gameDTO),
                        publisherService.readById(gameDTO.getPublisher()).orElseThrow()));
        return getOne(gameDTO.getId());
    }

    @DeleteMapping("/games/{id}")
    void delete(@PathVariable String id) {
        if(gameService.readById(id).isEmpty())
            throw new EntityStateException();
        gameService.deleteById(id);
    }
}
