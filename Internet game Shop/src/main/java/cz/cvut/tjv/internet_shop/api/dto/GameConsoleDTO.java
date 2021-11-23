package cz.cvut.tjv.internet_shop.api.dto;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.HashSet;
import java.util.Set;

public class GameConsoleDTO {

    @JsonView(GameConsoleViews.Public.class)
    private String id;

    @JsonView(GameConsoleViews.Public.class)
    private Double price;

    @JsonView(GameConsoleViews.Public.class)
    private Set<String> gamesForConsole = new HashSet<>();

    public GameConsoleDTO() {
    }

    public GameConsoleDTO(String id, Double price, Set<String> gamesForConsole) {
        this.id = id;
        this.price = price;
        this.gamesForConsole = gamesForConsole;
    }

    public String getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public Set<String> getGamesForConsole() {
        return gamesForConsole;
    }
}
