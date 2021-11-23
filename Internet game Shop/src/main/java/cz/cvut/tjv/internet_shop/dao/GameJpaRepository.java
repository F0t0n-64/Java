package cz.cvut.tjv.internet_shop.dao;

import cz.cvut.tjv.internet_shop.domain.Game;
import cz.cvut.tjv.internet_shop.domain.GameConsole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GameJpaRepository extends JpaRepository<Game, String> {

    @Query(value = "SELECT g.id FROM Game g WHERE g.publisher.id = :publisher")
    Set<String> findGamesByPublisher(@Param("publisher") String publisher);

    @Query(value = "SELECT g.id FROM Game g WHERE ?1 member of g.platformsForGame")
    Set<String> findByConsole(@Param("console") GameConsole console);
}
