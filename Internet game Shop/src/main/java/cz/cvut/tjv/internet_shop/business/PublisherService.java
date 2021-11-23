package cz.cvut.tjv.internet_shop.business;

import cz.cvut.tjv.internet_shop.dao.PublisherJpaRepository;
import cz.cvut.tjv.internet_shop.domain.Publisher;
import org.springframework.stereotype.Component;

@Component
public class PublisherService extends AbstractCrudService<String, Publisher, PublisherJpaRepository> {

    public PublisherService(PublisherJpaRepository repository) {
        super(repository);
    }

    @Override
    public boolean exists(Publisher entity) {
        return repository.existsById(entity.getId());
    }

}
