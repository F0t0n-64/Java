package cz.cvut.tjv.internet_shop.dao;

import cz.cvut.tjv.internet_shop.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherJpaRepository extends JpaRepository<Publisher, String> {}
