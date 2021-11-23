package cz.cvut.tjv.internet_shop.service;

import cz.cvut.tjv.internet_shop.business.GameService;
import cz.cvut.tjv.internet_shop.dao.GameJpaRepository;
import cz.cvut.tjv.internet_shop.domain.Game;
import cz.cvut.tjv.internet_shop.domain.GameConsole;
import cz.cvut.tjv.internet_shop.domain.Publisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GameServiceTests {

    @InjectMocks
    GameService gameService;

    @Mock
    GameJpaRepository repository;

    @Test
    public void testCreate() {

        Publisher publisher = new Publisher("Sony",12000);
        GameConsole playstationFive = new GameConsole("PlayStation5", 13000.00);
        GameConsole playstationFour = new GameConsole("PlayStation4", 6000.00);
        Set<GameConsole> gameConsoles = Set.of(playstationFive, playstationFour);
        Game game = new Game("God of War",1200.00, publisher, gameConsoles);
        gameService.create(game);

        verify(repository, times(1)).save(any());
        verify(repository, times(1)).save(any(Game.class));
        verify(repository, times(1)).save(game);
    }

    @Test
    public void testReadById() {

        Publisher publisher = new Publisher("Sony",12000);
        GameConsole playstationFive = new GameConsole("PlayStation5", 13000.00);
        GameConsole playstationFour = new GameConsole("PlayStation4", 6000.00);
        Set<GameConsole> gameConsoles = Set.of(playstationFive, playstationFour);
        Game game = new Game("God of War",1200.00, publisher, gameConsoles);

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(game));

        Game gameTest = gameService.readById(game.getId()).get();

        assertEquals(gameTest.getId(), game.getId());
        assertEquals(gameTest.getPrice(), game.getPrice());

        verify(repository, times(1)).findById(game.getId());

    }

    @Test
    public void testReadAll() {

        Publisher publisher = new Publisher("Sony",12000);
        GameConsole playstationFive = new GameConsole("PlayStation5", 13000.00);
        GameConsole playstationFour = new GameConsole("PlayStation4", 6000.00);
        Set<GameConsole> gameConsoles = Set.of(playstationFive, playstationFour);
        Game gameOne = new Game("God of War",1200.00, publisher, gameConsoles);
        Game gameTwo = new Game("The Last of Us",1300.00, publisher, gameConsoles);

        List<Game> games = List.of(gameOne, gameTwo);

        Mockito.when(repository.findAll()).thenReturn(games);

        Collection<Game> returnedGames = gameService.readAll();

        assertEquals(2, returnedGames.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testUpdate() {

        Publisher publisher = new Publisher("Sony",12000);
        GameConsole playstationFive = new GameConsole("PlayStation5", 13000.00);
        GameConsole playstationFour = new GameConsole("PlayStation4", 6000.00);
        Set<GameConsole> gameConsoles = Set.of(playstationFive, playstationFour);
        Game game = new Game("God of War",1200.00, publisher, gameConsoles);

        Mockito.when(repository.existsById(any())).thenReturn(true);
        gameService.update(game);

        verify(repository, times(1)).save(game);
    }

    @Test
    public void testDelete() {

        Publisher publisher = new Publisher("Sony",12000);
        GameConsole playstationFive = new GameConsole("PlayStation5", 13000.00);
        GameConsole playstationFour = new GameConsole("PlayStation4", 6000.00);
        Set<GameConsole> gameConsoles = Set.of(playstationFive, playstationFour);
        Game game = new Game("God of War",1200.00, publisher, gameConsoles);

        gameService.deleteById(game.getId());

        verify(repository, times(1)).deleteById(game.getId());
    }

    @Test
    public void testExists() {

        Publisher publisher = new Publisher("Sony",12000);
        GameConsole playstationFive = new GameConsole("PlayStation5", 13000.00);
        GameConsole playstationFour = new GameConsole("PlayStation4", 6000.00);
        Set<GameConsole> gameConsoles = Set.of(playstationFive, playstationFour);
        Game game = new Game("God of War",1200.00, publisher, gameConsoles);

        Mockito.when(gameService.exists(game)).thenReturn(true);
        gameService.exists(game);

        verify(repository, times(1)).existsById(game.getId());
    }

    @Test
    public void testFindByPublisher() {

        Publisher publisher = new Publisher("Sony", 123456789);
        Set<String> gamesByPublisher = Set.of("STALKER", "The Order 1886");

        Mockito.when(gameService.findByPublisher(publisher.getId())).thenReturn(gamesByPublisher);
        gameService.findByPublisher(publisher.getId());

        verify(repository, times(1)).findGamesByPublisher(publisher.getId());

    }

    @Test
    public void testFindByConsole() {

        GameConsole gameConsole = new GameConsole("PlayStation5", 13000.00);
        Set<String> gamesByConsole = Set.of("The Witcher", "Horizon Zero Down");

        Mockito.when(gameService.findByConsole(gameConsole)).thenReturn(gamesByConsole);
        gameService.findByConsole(gameConsole);

        verify(repository, times(1)).findByConsole(gameConsole);
    }
}
