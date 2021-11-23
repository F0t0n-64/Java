package cz.cvut.tjv.internet_shop.api.converter;

import cz.cvut.tjv.internet_shop.api.dto.PublisherDTO;
import cz.cvut.tjv.internet_shop.domain.Publisher;

import java.util.Set;

public class PublisherConverter {

    public static Publisher toModel(PublisherDTO publisherDTO) {
        return new Publisher(publisherDTO.getId(), publisherDTO.getIncome());
    }

    public static PublisherDTO fromModel(Publisher publisher, Set<String> publishersGames) {
        return new PublisherDTO(publisher.getId(), publisher.getIncome(), publishersGames);
    }

}
