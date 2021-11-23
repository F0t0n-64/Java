package cz.cvut.fit.tjv.social_network.system;

import cz.cvut.fit.tjv.social_network.api.dto.UserDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class UserSystemTests {

    @Test
    public void testCreateReadDelete() {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/users";

        UserDTO userDTO = new UserDTO("TestUser1", null, null);

        ResponseEntity<UserDTO> entity = restTemplate.postForEntity(url, userDTO, UserDTO.class);

        UserDTO[] userDTOs = restTemplate.getForObject(url, UserDTO[].class);
        Assertions.assertThat(userDTOs).extracting(UserDTO::getUsername).containsAnyOf("TestUser1");

        restTemplate.delete(url + "/" + Objects.requireNonNull(entity.getBody()).getUsername());
        Assertions.assertThat(restTemplate.getForObject(url, UserDTO[].class)).isEmpty();
    }

    @Test
    public void testHttpStatusNotFound() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/wrongurl";

        try {
            restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            Assertions.assertThat(e.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}
