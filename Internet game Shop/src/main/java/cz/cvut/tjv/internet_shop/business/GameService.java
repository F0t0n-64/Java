package cz.cvut.tjv.internet_shop.business;

import cz.cvut.tjv.internet_shop.dao.GameJpaRepository;
import cz.cvut.tjv.internet_shop.domain.Game;
import cz.cvut.tjv.internet_shop.domain.GameConsole;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class GameService extends AbstractCrudService<String, Game, GameJpaRepository> {

    public GameService(GameJpaRepository repository) {
        super(repository);
    }

    @Override
    public boolean exists(Game entity) {
        return repository.existsById(entity.getId());
    }

    public Set<String> findByPublisher(String idPublisher) {
        return repository.findGamesByPublisher(idPublisher);
    }

    public Set<String> findByConsole(GameConsole console) {
        return repository.findByConsole(console);
    }

}
