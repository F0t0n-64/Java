package cz.cvut.tjv.internet_shop.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Game implements Serializable {

    @Id
    @Column(name = "game_name")
    private String id;

    @Column(name = "game_price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "game_publisher")
    private Publisher publisher;

    @ManyToMany
    @JoinTable(name = "games_and_consoles",
            joinColumns = @JoinColumn(name = "game_console_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id"))
    private Set<GameConsole> platformsForGame = new HashSet<>();

    public Game() {
    }

    public Game(String id, Double price, Publisher publisher, Set<GameConsole> platformsForGame) {
        this.id = id;
        this.price = price;
        this.publisher = publisher;
        this.platformsForGame = platformsForGame;
    }

    public String getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Set<GameConsole> getPlatformsForGame() {
        return platformsForGame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) && Objects.equals(price, game.price) && Objects.equals(publisher, game.publisher) && Objects.equals(platformsForGame, game.platformsForGame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, publisher, platformsForGame);
    }
}
