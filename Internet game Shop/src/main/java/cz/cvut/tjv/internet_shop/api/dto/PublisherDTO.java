package cz.cvut.tjv.internet_shop.api.dto;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.HashSet;
import java.util.Set;

public class PublisherDTO {

    @JsonView(PublisherViews.Public.class)
    private String id;

    @JsonView(PublisherViews.Public.class)
    private Integer income;

    @JsonView(PublisherViews.Public.class)
    private Set<String> publishedGames = new HashSet<>();

    public PublisherDTO(String id, Integer income, Set<String> publishedGames) {
        this.id = id;
        this.income = income;
        this.publishedGames = publishedGames;
    }

    public PublisherDTO() {
    }

    public Set<String> getPublishedGames() {
        return publishedGames;
    }

    public String getId() {
        return id;
    }

    public int getIncome() {
        return income;
    }
}
