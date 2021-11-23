package cz.cvut.tjv.internet_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.tjv.internet_shop.api.controller.GameConsoleController;
import cz.cvut.tjv.internet_shop.api.converter.GameConsoleConverter;
import cz.cvut.tjv.internet_shop.api.dto.GameConsoleDTO;
import cz.cvut.tjv.internet_shop.business.GameConsoleService;
import cz.cvut.tjv.internet_shop.business.GameService;
import cz.cvut.tjv.internet_shop.domain.GameConsole;
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

@WebMvcTest(GameConsoleController.class)
public class GameConsoleControllerTests {

    @MockBean
    GameService gameService;

    @MockBean
    GameConsoleService gameConsoleService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAll() throws Exception {

        GameConsole playStation5 = new GameConsole("PlayStation5", 13000.00);
        GameConsole playStation4 = new GameConsole("PlayStation4", 6000.00);

        List<GameConsole> gameConsoles = List.of(playStation5, playStation4);

        Mockito.when(gameConsoleService.readAll()).thenReturn(gameConsoles);

        mockMvc.perform(get("/gameConsole"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is("PlayStation5")))
                .andExpect(jsonPath("$[1].id", Matchers.is("PlayStation4")));
    }

    @Test
    public void testGetOne() throws Exception {

        GameConsole gameConsole = new GameConsole("XBox Serias X", 13000.00);

        Mockito.when(gameConsoleService.readById(Mockito.anyString())).thenReturn(java.util.Optional.of(gameConsole));

        mockMvc.perform(get("/gameConsole/XBox Serias X"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is("XBox Serias X")))
                .andExpect(jsonPath("$.price", Matchers.is(13000.00)));
    }

    @Test
    public void createTest() throws Exception {

        Set<String> games = Set.of("Dark souls", "Elden Ring");
        GameConsoleDTO gameConsoleDTO = new GameConsoleDTO("XBox One", 5000.00, games);
        GameConsole gameConsole = GameConsoleConverter.toModel(gameConsoleDTO);
        ObjectMapper objectMapper = new ObjectMapper();

        Mockito.when(gameConsoleService.readById(Mockito.anyString())).thenReturn(java.util.Optional.of(gameConsole));

        var postRequest = post("/gameConsole")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(gameConsoleDTO));

        mockMvc.perform(postRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(gameConsoleDTO.getId())));

        verify(gameConsoleService, times(1)).create(gameConsole);
    }

    @Test
    public void testUpdate() throws Exception {

        Set<String> games = Set.of("Assassins Creed", "Gothic");
        GameConsoleDTO gameConsoleDTO = new GameConsoleDTO("PlayStation4", 6000.00, games);
        GameConsole gameConsole = GameConsoleConverter.toModel(gameConsoleDTO);
        ObjectMapper objectMapper = new ObjectMapper();

        Mockito.when(gameConsoleService.readById(gameConsoleDTO.getId())).thenReturn(Optional.of(gameConsole));

        var putRequest = put("/gameConsole/PlayStation4")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(gameConsoleDTO));

        mockMvc.perform(putRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(gameConsoleDTO.getId())))
                .andExpect(jsonPath("$.price", Matchers.is(6000.00)));

        verify(gameConsoleService, times(1)).update(gameConsole);
    }

    @Test
    public void testDelete() throws Exception {

        GameConsole gameConsolePs5 = new GameConsole("PlayStation5", 13000.00);

        Mockito.when(gameConsoleService.readById(gameConsolePs5.getId())).thenReturn(Optional.of(gameConsolePs5));

        mockMvc.perform(delete("/gameConsole/PlayStation5"))
                .andExpect(status().isOk());

        verify(gameConsoleService, times(1)).deleteById(gameConsolePs5.getId());
    }
}
