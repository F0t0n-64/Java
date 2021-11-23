package cz.cvut.tjv.internet_shop.service;

import cz.cvut.tjv.internet_shop.business.PublisherService;
import cz.cvut.tjv.internet_shop.dao.PublisherJpaRepository;
import cz.cvut.tjv.internet_shop.domain.Publisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PublisherServiceTests {

    @InjectMocks
    PublisherService publisherService;

    @Mock
    PublisherJpaRepository repository;

    @Test
    public void testCreate() {
        Publisher publisher = new Publisher("Sony", 9999999);
        publisherService.create(publisher);
        verify(repository, times(1)).save(any());
        verify(repository, times(1)).save(any(Publisher.class));
        verify(repository, times(1)).save(publisher);
    }

    @Test
    public void testReadById() {

        Publisher publisher = new Publisher("Sony", 9999999);

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(publisher));

        Publisher publisherTest = publisherService.readById(publisher.getId()).get();

        assertEquals(publisherTest.getId(), publisher.getId());

        verify(repository, times(1)).findById(any());

    }

    @Test
    public void testReadAll() {
        Publisher sony = new Publisher("Sony", 9999999);
        Publisher microsoft = new Publisher("Microsoft", 1876543);
        List<Publisher> publishers = List.of(sony, microsoft);

        Mockito.when(repository.findAll()).thenReturn(publishers);

        Collection<Publisher> returnedPublishers = publisherService.readAll();

        assertEquals(2, returnedPublishers.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testUpdate() {

        Publisher sony = new Publisher("Sony", 120000);

        Mockito.when(repository.existsById(any())).thenReturn(true);
        publisherService.update(sony);

        verify(repository, times(1)).save(sony);
    }

    @Test
    public void testDelete() {

        Publisher sony = new Publisher("Sony", 120000);
        publisherService.deleteById(sony.getId());

        verify(repository, times(1)).deleteById(sony.getId());
    }

    @Test
    public void testExists() {

        Publisher sony = new Publisher("Sony", 120000);

        Mockito.when(publisherService.exists(sony)).thenReturn(true);
        publisherService.exists(sony);

        verify(repository, times(1)).existsById(sony.getId());
    }
}

