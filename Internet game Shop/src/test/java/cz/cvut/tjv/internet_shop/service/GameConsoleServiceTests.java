package cz.cvut.tjv.internet_shop.service;

import cz.cvut.tjv.internet_shop.business.GameConsoleService;
import cz.cvut.tjv.internet_shop.dao.GameConsoleJpaRepository;
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
public class GameConsoleServiceTests {

    @InjectMocks
    GameConsoleService gameConsoleService;

    @Mock
    GameConsoleJpaRepository repository;

    @Test
    public void testCreate() {

        GameConsole gameConsole = new GameConsole("PlayStation5", 13000.00);

        gameConsoleService.create(gameConsole);

        verify(repository, times(1)).save(any());
        verify(repository, times(1)).save(any(GameConsole.class));
        verify(repository, times(1)).save(gameConsole);
    }

    @Test
    public void testReadById() {

        GameConsole gameConsole = new GameConsole("PlayStation5", 13000.00);

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(gameConsole));

        GameConsole gameConsoleTest = gameConsoleService.readById(gameConsole.getId()).get();

        assertEquals(gameConsoleTest.getId(), gameConsole.getId());
        assertEquals(gameConsoleTest.getPrice(), gameConsole.getPrice());

        verify(repository, times(1)).findById(gameConsole.getId());
    }

    @Test
    public void testReadAll() {

        GameConsole playStation5 = new GameConsole("PlayStation5", 13000.00);
        GameConsole playStation4 = new GameConsole("PlayStation4", 6000.00);
        List<GameConsole> gameConsoles = List.of(playStation5, playStation4);

        Mockito.when(repository.findAll()).thenReturn(gameConsoles);

        Collection<GameConsole> returnedGameConsoles = gameConsoleService.readAll();

        assertEquals(2, returnedGameConsoles.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testUpdate() {

        GameConsole playStation5 = new GameConsole("PlayStation5", 13000.00);

        Mockito.when(repository.existsById(any())).thenReturn(true);
        gameConsoleService.update(playStation5);

        verify(repository, times(1)).save(playStation5);
    }

    @Test
    public void testDelete() {

        GameConsole playStation5 = new GameConsole("PlayStation5", 13000.00);

        gameConsoleService.deleteById(playStation5.getId());

        verify(repository, times(1)).deleteById(playStation5.getId());
    }

    @Test
    public void testExists() {

        GameConsole playStation5 = new GameConsole("PlayStation5", 13000.00);

        Mockito.when(gameConsoleService.exists(playStation5)).thenReturn(true);
        gameConsoleService.exists(playStation5);

        verify(repository, times(1)).existsById(playStation5.getId());
    }

    @Test
    public void testFindByGame() {
        Publisher publisher = new Publisher("Sony",12000);
        GameConsole playstation5 = new GameConsole("PlayStation5", 13000.00);
        GameConsole playstation4 = new GameConsole("PlayStation4", 6000.00);
        Set<GameConsole> gameConsoles = Set.of(playstation5, playstation4);
        Game game = new Game("God of War",1200.00, publisher, gameConsoles);

        Set<String> gamesByPublisher = Set.of("Resident Evil", "Sekiro");

        Mockito.when(gameConsoleService.findByGame(game)).thenReturn(gamesByPublisher);
        gameConsoleService.findByGame(game);

        verify(repository, times(1)).findByGame(game);
    }
}
