package cz.cvut.fit.tjv.social_network.integration;

import cz.cvut.fit.tjv.social_network.api.controller.UserController;
import cz.cvut.fit.tjv.social_network.api.dto.UserDTO;
import cz.cvut.fit.tjv.social_network.api.exception.NoEntityFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserIntegrationTests {

    final static String USER_ID = "TestUser1";

    @Autowired
    UserController userController;

    @Test
    public void testCreateRead() {
        UserDTO userDTO = new UserDTO(USER_ID, null, null);

        UserDTO resultDTO = userController.create(userDTO);

        Collection<UserDTO> userDTOs = userController.getAll();

        Assertions.assertThat(userDTOs)
                .anyMatch(user -> Objects.equals(user.getUsername(), USER_ID));

        userController.delete(USER_ID);

        Assertions.assertThat(userController.getAll())
                .noneMatch(user -> Objects.equals(user.getUsername(), USER_ID));
    }

    @Test
    public void testThrowsNoEntityFoundException() {
        Assertions.assertThatExceptionOfType(NoEntityFoundException.class)
                .isThrownBy(() -> userController.getOne(USER_ID));
    }
}
