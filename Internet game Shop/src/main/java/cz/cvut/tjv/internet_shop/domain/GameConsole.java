package cz.cvut.tjv.internet_shop.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class GameConsole implements Serializable {

    @Id
    private String name;

    private Double price;

    @ManyToMany(mappedBy = "platformsForGame")
    Set<Game> gamesForConsole = new HashSet<>();

    public GameConsole() {
    }

    public GameConsole(String id, Double price) {
        this.name = id;
        this.price = price;
    }

    public String getId() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Set<Game> getGamesForConsole() {
        return gamesForConsole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameConsole that = (GameConsole) o;
        return name.equals(that.name) && price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
