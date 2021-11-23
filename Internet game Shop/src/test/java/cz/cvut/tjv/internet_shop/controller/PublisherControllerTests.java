package cz.cvut.tjv.internet_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.tjv.internet_shop.api.controller.PublisherController;
import cz.cvut.tjv.internet_shop.api.converter.PublisherConverter;
import cz.cvut.tjv.internet_shop.api.dto.PublisherDTO;
import cz.cvut.tjv.internet_shop.business.GameService;
import cz.cvut.tjv.internet_shop.business.PublisherService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.verify;

@WebMvcTest(PublisherController.class)
public class PublisherControllerTests {

    @MockBean
    PublisherService publisherService;

    @MockBean
    GameService gameService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAll() throws Exception {

        Publisher publisherOne = new Publisher("Sony",120000);
        Publisher publisherTwo = new Publisher("EArts",10000);

        List<Publisher> publishers = List.of(publisherOne, publisherTwo);

        Mockito.when(publisherService.readAll()).thenReturn(publishers);

        mockMvc.perform(get("/publishers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is("Sony")))
                .andExpect(jsonPath("$[1].id", Matchers.is("EArts")));
    }

    @Test
    public void testGetOne() throws Exception {

        Publisher publisher = new Publisher("Blizzard",990000);

        Mockito.when(publisherService.readById(Mockito.anyString())).thenReturn(java.util.Optional.of(publisher));

        mockMvc.perform(get("/publishers/Blizzard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is("Blizzard")))
                .andExpect(jsonPath("$.income", Matchers.is(990000)));
    }

    @Test
    public void createTest() throws Exception {

        PublisherDTO publisherDTO = new PublisherDTO("Sony",99999, new HashSet<>());
        ObjectMapper objectMapper = new ObjectMapper();
        Publisher publisher = PublisherConverter.toModel(publisherDTO);

        Mockito.when(publisherService.readById(publisherDTO.getId())).thenReturn(Optional.of(publisher));

        var postRequest = post("/publishers")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(publisherDTO));

        mockMvc.perform(postRequest)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", Matchers.is(publisherDTO.getId())));

        verify(publisherService, times(1)).create(publisher);
    }

    @Test
    public void testUpdate() throws Exception {

        PublisherDTO publisherDTO = new PublisherDTO("GamesTech",10000000, new HashSet<>());
        Publisher publisherByPut = PublisherConverter.toModel(publisherDTO);
        ObjectMapper objectMapper = new ObjectMapper();

        Mockito.when(publisherService.readById(publisherDTO.getId())).thenReturn(Optional.of(publisherByPut));

        var putRequest = put("/publishers/GamesTech")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(publisherDTO));

        mockMvc.perform(putRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(publisherDTO.getId())))
                .andExpect(jsonPath("$.income", Matchers.is(publisherDTO.getIncome())));

        verify(publisherService, times(1)).update(publisherByPut);
    }

    @Test
    public void testDelete() throws Exception {

        Publisher publisher = new Publisher("CVUT_FIT",123123123);

        Mockito.when(publisherService.readById(publisher.getId())).thenReturn(Optional.of(publisher));

        mockMvc.perform(delete("/publishers/CVUT_FIT"))
                .andExpect(status().isOk());

        verify(publisherService, times(1)).deleteById(publisher.getId());
    }
}


