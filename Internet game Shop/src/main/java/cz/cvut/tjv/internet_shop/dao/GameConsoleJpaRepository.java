package cz.cvut.tjv.internet_shop.dao;

import cz.cvut.tjv.internet_shop.domain.Game;
import cz.cvut.tjv.internet_shop.domain.GameConsole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GameConsoleJpaRepository extends JpaRepository<GameConsole, String> {

    @Query(value = "SELECT g.name FROM GameConsole g WHERE ?1 member of g.gamesForConsole")
    Set<String> findByGame(@Param("game") Game game);
}
