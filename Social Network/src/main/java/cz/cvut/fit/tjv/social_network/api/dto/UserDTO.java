package cz.cvut.fit.tjv.social_network.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDTO {

    @JsonView(UserViews.Public.class)
    public String username;

    @JsonView(UserViews.Public.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "d.M.yyyy")
    public LocalDate dateOfBirth;

    @JsonView(UserViews.Admin.class)
    //@JsonIgnore
    public LocalDateTime lastLogin;

    public UserDTO() {
    }

    public UserDTO(String username, LocalDate dateOfBirth, LocalDateTime lastLogin) {
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.lastLogin = lastLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
}
