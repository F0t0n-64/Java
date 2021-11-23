package cz.cvut.fit.tjv.social_network.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fit.tjv.social_network.api.converter.UserConverter;
import cz.cvut.fit.tjv.social_network.api.dto.UserDTO;
import cz.cvut.fit.tjv.social_network.api.dto.UserViews;
import cz.cvut.fit.tjv.social_network.api.exception.NoEntityFoundException;
import cz.cvut.fit.tjv.social_network.business.EntityStateException;
import cz.cvut.fit.tjv.social_network.business.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @JsonView(UserViews.Public.class)
    @GetMapping("/users")
    public Collection<UserDTO> getAll() {
        return UserConverter.fromModels(userService.readAll());
    }

    @JsonView(UserViews.Admin.class)
    @GetMapping("/admin/users")
    public Collection<UserDTO> getAllAdmin() {
        return UserConverter.fromModels(userService.readAll());
    }

    @GetMapping("/users/{id}")
    public UserDTO getOne(@PathVariable String id) {
        return UserConverter.fromModel(userService.readById(id).orElseThrow(NoEntityFoundException::new));
    }

    @PostMapping("/users")
    public UserDTO create(@RequestBody UserDTO userDTO) {
        userService.create(UserConverter.toModel(userDTO));
        return getOne(userDTO.getUsername());
    }

    @PutMapping("/users/{id}")
    public UserDTO update(@PathVariable String id, @RequestBody UserDTO userDTO) {
        if(!userDTO.getUsername().equals(id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ids do not match");
        userService.update(UserConverter.toModel(userDTO));
        return getOne(userDTO.getUsername());
    }

    //Should return 204
    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable String id) {
        if(userService.readById(id).isEmpty())
            throw new EntityStateException();
        userService.deleteById(id);
    }
}
