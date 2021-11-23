package cz.cvut.tjv.internet_shop.business;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

public abstract class AbstractCrudService<K, E, REPOSITORY extends JpaRepository<E, K>> {
    protected final REPOSITORY repository;

    public AbstractCrudService(REPOSITORY repository) {
        this.repository = repository;
    }

    public abstract boolean exists(E entity);

    @Transactional
    public void create(E entity) throws EntityStateException {
        if(exists(entity))
            throw new EntityStateException(entity);
        repository.save(entity);
    }

    public Optional<E> readById(K id) {
        return repository.findById(id);
    }

    public Collection<E> readAll() {
        return repository.findAll();
    }

    @Transactional
    public void update(E entity) throws EntityStateException {
        if(exists(entity))
            repository.save(entity);
        else
            throw new EntityStateException(entity);
    }

    public void deleteById(K id) {
        repository.deleteById(id);
    }
}
