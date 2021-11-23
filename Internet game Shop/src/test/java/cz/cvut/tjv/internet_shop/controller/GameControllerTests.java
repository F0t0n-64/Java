package cz.cvut.tjv.internet_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.tjv.internet_shop.api.controller.GameController;
import cz.cvut.tjv.internet_shop.api.converter.GameConverter;
import cz.cvut.tjv.internet_shop.api.dto.GameDTO;
import cz.cvut.tjv.internet_shop.business.GameConsoleService;
import cz.cvut.tjv.internet_shop.business.GameService;
import cz.cvut.tjv.internet_shop.business.PublisherService;
import cz.cvut.tjv.internet_shop.domain.Game;
import cz.cvut.tjv.internet_shop.domain.GameConsole;
import cz.cvut.tjv.internet_shop.domain.Publisher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.*;
import static org.mockito.Mockito.verify;

@WebMvcTest(GameController.class)
public class GameControllerTests {

    @MockBean
    GameService gameService;

    @MockBean
    PublisherService publisherService;

    @MockBean
    GameConsoleService gameConsoleService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAll() throws Exception {

        Publisher publisher = new Publisher("Sony",12000);
        Game gameOne = new Game("God of War",1200.00, publisher, new HashSet<>());
        Game gameTwo = new Game("The Last of Us",1200.00, publisher, new HashSet<>());

        Collection<Game> games = List.of(gameOne, gameTwo);

        Mockito.when(gameService.readAll()).thenReturn(games);

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is("God of War")))
                .andExpect(jsonPath("$[1].id", Matchers.is("The Last of Us")));
    }

    @Test
    public void testGetOne() throws Exception {

        Publisher publisher = new Publisher("Sony",12000);
        Game game = new Game("God of War",1200.00, publisher, new HashSet<>());

        Mockito.when(gameService.readById(Mockito.anyString())).thenReturn(java.util.Optional.of(game));

        mockMvc.perform(get("/games/God of War"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is("God of War")))
                .andExpect(jsonPath("$.price", Matchers.is(1200.00)));
    }

    @Test
    public void createTest() throws Exception {

        Publisher publisher = new Publisher("EArts",100000);
        GameDTO gameDTO = new GameDTO("Fifa", 1000.00, publisher.getId(), new HashSet<>());
        GameConsole gameConsole = new GameConsole("PlayStation5", 13000.00);
        Set<GameConsole> gameConsoles = Set.of(gameConsole);
        Game game = GameConverter.toModel(gameDTO, gameConsoles, publisher);
        ObjectMapper objectMapper = new ObjectMapper();

        Mockito.when(gameService.readById(Mockito.anyString())).thenReturn(java.util.Optional.of(game));
        Mockito.when(publisherService.readById(Mockito.anyString())).thenReturn(java.util.Optional.of(publisher));
        Mockito.when(gameConsoleService.getGameConsoles(Mockito.any())).thenReturn(gameConsoles);

        var postRequest = post("/games")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(gameDTO));

        mockMvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(gameDTO.getId())));

        verify(gameService, times(1)).create(game);
    }

    @Test
    public void testUpdate() throws Exception {

        Publisher publisher = new Publisher("Activisoin",199999);
        GameDTO gameDTO = new GameDTO("Call of Duty", 599.99, publisher.getId(), new HashSet<>());
        GameConsole gameConsolePs5 = new GameConsole("PlayStation5", 13000.00);
        GameConsole gameConsolePs4 = new GameConsole("PlayStation4", 6000.00);
        Set<GameConsole> gameConsoles = Set.of(gameConsolePs5, gameConsolePs4);
        Game game = GameConverter.toModel(gameDTO, gameConsoles, publisher);
        ObjectMapper objectMapper = new ObjectMapper();

        Mockito.when(gameService.readById(gameDTO.getId())).thenReturn(Optional.of(game));
        Mockito.when(publisherService.readById(Mockito.anyString())).thenReturn(java.util.Optional.of(publisher));
        Mockito.when(gameConsoleService.getGameConsoles(Mockito.any())).thenReturn(gameConsoles);

        var putRequest = put("/games/Call of Duty")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(gameDTO));

        mockMvc.perform(putRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(gameDTO.getId())))
                .andExpect(jsonPath("$.price", Matchers.is(599.99)));

        verify(gameService, times(1)).update(game);
    }

    @Test
    public void testDelete() throws Exception {

        Publisher publisher = new Publisher("Blizzard",1998000);
        Game game = new Game("Heroes of the Storm",300.00, publisher, new HashSet<>());

        Mockito.when(gameService.readById(game.getId())).thenReturn(Optional.of(game));

        mockMvc.perform(delete("/games/Heroes of the Storm"))
                .andExpect(status().isOk());

        verify(gameService, times(1)).deleteById(game.getId());
    }
}
