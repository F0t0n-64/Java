package cz.cvut.fit.tjv.social_network.dao;

import cz.cvut.fit.tjv.social_network.domain.Post;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Primary
@Repository
public interface PostJpaRepository extends JpaRepository<Post, LocalDateTime> {

    // SQL generated from method name
    List<Post> findAllByLikedUsername(String username);

    // Native SQL
    @Query(nativeQuery = true, value = "SELECT p.* FROM post p " +
            "JOIN user_post_like l ON l.post_created=p.created " +
            "WHERE l.user_username = :username")
    List<Post> findAllLikedByUserNative(String username);

    // JPQL syntax
    @Query(value = "SELECT p FROM Post p JOIN p.liked u WHERE u.username = :username")
    List<Post> findAllLikedByUserJPQL(String username);
}
