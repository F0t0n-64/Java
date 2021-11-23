package cz.cvut.tjv.internet_shop.application;

import cz.cvut.tjv.internet_shop.api.controller.PublisherController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestServerTest {

    @Autowired
    PublisherController publisherController;

    @Test
    public void testContextLoadsController() {
        Assertions.assertThat(publisherController).isNotNull();
    }

}
