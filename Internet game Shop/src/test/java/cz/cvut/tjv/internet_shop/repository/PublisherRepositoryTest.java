package cz.cvut.tjv.internet_shop.repository;

import cz.cvut.tjv.internet_shop.dao.PublisherJpaRepository;
import cz.cvut.tjv.internet_shop.domain.Publisher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Collection;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PublisherRepositoryTest {

    @Autowired
    PublisherJpaRepository repository;

    @Test
    public void testCreateReadDelete() {
        Publisher publisher = new Publisher("Sony", 9999999);

        repository.save(publisher);

        Collection<Publisher> publishers = repository.findAll();
        Assertions.assertThat(publishers).extracting(Publisher::getId).contains("Sony");

        repository.deleteAll();
        Assertions.assertThat(repository.findAll()).isEmpty();
    }

}

