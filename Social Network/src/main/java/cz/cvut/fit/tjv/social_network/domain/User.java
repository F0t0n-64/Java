/*
 * Project Social Network. Created for Java Technology course at Czech Technical University in Prague,
 * Faculty of Information Technology.
 *
 * Author: Ondřej Guth (ondrej.guth@fit.cvut.cz)
 *
 * This code is intended for educational purposes only.
 */

package cz.cvut.fit.tjv.social_network.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Domain type User. Its primary key is username (String).
 */
@Entity(name = "user_account")
public class User implements Serializable { //Serializable may be used by ObjectInputStream and ObjectOutputStream
    @Serial
    private final static long serialVersionUID = 5793992981182732033L;

    /**
     * primary key of User
     */
    @Id
    private String username; //final prevents second assignment (past a constructor)

    private LocalDate dateOfBirth;

    private LocalDateTime lastLogin;

    /**
     * Store given username in the instance.
     *
     * @param username given username; cannot be null
     * @throws NullPointerException if the given username is null
     */
    public User(String username) {
        this.username = Objects.requireNonNull(username);
    }

    public User(String username, LocalDate dateOfBirth, LocalDateTime lastLogin) {
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.lastLogin = lastLogin;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    /**
     * Compare this and another instance of User by username.
     *
     * @param o other user to compare
     * @return true if other instance is also User and has the same username
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return getUsername().equals(user.getUsername());
    }

    @Override
    public int hashCode() {
        return getUsername().hashCode();
    } //hashCode must work the same way as equals

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
