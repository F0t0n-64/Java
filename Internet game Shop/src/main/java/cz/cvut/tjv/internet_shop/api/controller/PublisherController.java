package cz.cvut.tjv.internet_shop.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.tjv.internet_shop.api.converter.PublisherConverter;
import cz.cvut.tjv.internet_shop.api.dto.PublisherDTO;
import cz.cvut.tjv.internet_shop.api.dto.PublisherViews;
import cz.cvut.tjv.internet_shop.api.exception.NoEntityFoundException;
import cz.cvut.tjv.internet_shop.business.EntityStateException;
import cz.cvut.tjv.internet_shop.business.GameService;
import cz.cvut.tjv.internet_shop.business.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class PublisherController {

    PublisherService publisherService;
    GameService gameService;

    public PublisherController(PublisherService publisherService, GameService gameService) {
        this.publisherService = publisherService;
        this.gameService = gameService;
    }

    @JsonView(PublisherViews.Public.class)
    @GetMapping("/publishers")
    Collection<PublisherDTO> getAll() {
        return publisherService.readAll().
                stream().
                map(i -> PublisherConverter.fromModel(i, gameService.findByPublisher(i.getId()))).
                collect(Collectors.toList());
    }

    @GetMapping("/publishers/{id}")
    PublisherDTO getOne(@PathVariable String id) {
        return PublisherConverter.fromModel(
                publisherService.readById(id).orElseThrow(NoEntityFoundException::new),
                gameService.findByPublisher(id)
        );
    }

    @PostMapping("/publishers")
    PublisherDTO create(@RequestBody PublisherDTO publisherDTO) {
        publisherService.create(PublisherConverter.toModel(publisherDTO));
        return getOne(publisherDTO.getId());
    }

    @PutMapping("/publishers/{id}")
    PublisherDTO update(@PathVariable String id, @RequestBody PublisherDTO publisherDTO) {
        if(! publisherDTO.getId().equals(id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Publisher doesn't exists");
        publisherService.update(PublisherConverter.toModel(publisherDTO));
        return getOne(publisherDTO.getId());
    }

    @DeleteMapping("/publishers/{id}")
    void delete(@PathVariable String id) {
        if(publisherService.readById(id).isEmpty())
            throw new EntityStateException();
        publisherService.deleteById(id);
    }
}
