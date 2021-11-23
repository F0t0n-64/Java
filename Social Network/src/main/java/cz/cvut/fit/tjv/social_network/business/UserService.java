/*
 * Project Social Network. Created for Java Technology course at Czech Technical University in Prague,
 * Faculty of Information Technology.
 *
 * Author: Ondřej Guth (ondrej.guth@fit.cvut.cz)
 *
 * This code is intended for educational purposes only.
 */

package cz.cvut.fit.tjv.social_network.business;

import cz.cvut.fit.tjv.social_network.dao.UserJpaRepository;
import cz.cvut.fit.tjv.social_network.domain.User;
import org.springframework.stereotype.Component;

/**
 * Business logic operations related to User domain type.
 */
@Component
public class UserService extends AbstractCrudService<String, User, UserJpaRepository> {
    public UserService(UserJpaRepository userJpaRepository) {
        super(userJpaRepository);
    }

    @Override
    public boolean exists(User entity) {
        return repository.existsById(entity.getUsername());
    }
}
