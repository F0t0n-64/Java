package cz.cvut.fit.tjv.social_network.controller;

import cz.cvut.fit.tjv.social_network.api.controller.UserController;
import cz.cvut.fit.tjv.social_network.business.UserService;
import cz.cvut.fit.tjv.social_network.domain.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetAll() throws Exception {
        User user1 = new User("abc");
        User user2 = new User("xyz");
        List<User> users = List.of(user1, user2);

        Mockito.when(userService.readAll()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].username", Matchers.is("abc")))
                .andExpect(jsonPath("$[1].username", Matchers.is("xyz")));
    }
}
