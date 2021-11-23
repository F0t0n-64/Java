package cz.cvut.fit.tjv.social_network.application;

import cz.cvut.fit.tjv.social_network.api.controller.UserController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestServerTests {

    @Autowired
    UserController userController;

    @Test
    public void testContextLoadsController() {
        Assertions.assertThat(userController).isNotNull();
    }
}
