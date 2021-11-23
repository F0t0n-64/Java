package cz.cvut.fit.tjv.social_network.api.converter;

import cz.cvut.fit.tjv.social_network.api.dto.UserDTO;
import cz.cvut.fit.tjv.social_network.domain.User;

import java.util.Collection;

public class UserConverter {

    public static User toModel(UserDTO userDTO) {
        return new User(userDTO.getUsername(), userDTO.getDateOfBirth(), userDTO.getLastLogin());
    }

    public static UserDTO fromModel(User user) {
        return new UserDTO(user.getUsername(), user.getDateOfBirth(), user.getLastLogin());
    }

    public static Collection<User> toModels(Collection<UserDTO> userDTOs) {
/*        Collection<User> users = new ArrayList<>();
        userDTOs.forEach((userDTO) -> users.add(toModel(userDTO)));
        return users;*/

//        return userDTOs.stream().map((userDTO) -> toModel(userDTO)).toList();

        return userDTOs.stream().map(UserConverter::toModel).toList();
    }

    public static Collection<UserDTO> fromModels(Collection<User> users) {
        return users.stream().map(UserConverter::fromModel).toList();
    }

}
