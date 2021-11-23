package cz.cvut.tjv.internet_shop.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.HashSet;
import java.util.Set;

public class GameDTO {

    @JsonView(GameViews.Public.class)
    private String id;

    @JsonView(GameViews.Public.class)
    private Double price;

    @JsonView(GameViews.Public.class)
    private String publisher;

    @JsonView(GameViews.Public.class)
    private Set<String> platformsForGame = new HashSet<>();

    public GameDTO() {}

    public GameDTO(String id, Double price, String publisher, Set<String> platformsForGame) {
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

    public String getPublisher() {
        return publisher;
    }

    public Set<String> getPlatformsForGame() {
        return platformsForGame;
    }
}
