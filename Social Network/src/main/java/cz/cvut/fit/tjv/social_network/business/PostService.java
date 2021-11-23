/*
 * Project Social Network. Created for Java Technology course at Czech Technical University in Prague,
 * Faculty of Information Technology.
 *
 * Author: Ondřej Guth (ondrej.guth@fit.cvut.cz)
 *
 * This code is intended for educational purposes only.
 */

package cz.cvut.fit.tjv.social_network.business;

import cz.cvut.fit.tjv.social_network.dao.PostJpaRepository;
import cz.cvut.fit.tjv.social_network.domain.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Business logic operations related to any Post.
 */
@Component
public class PostService extends AbstractCrudService<LocalDateTime, Post, PostJpaRepository> {
    protected UserService userService;

    public PostService(PostJpaRepository repository, UserService userService) {
        super(repository); //invocation of constructor of the superclass (AbstractCrudService)
//        repository = AbstractDaoFactory.getFactory().getPostRepository();
        this.userService = userService;
    }

//    public void setUserService(UserService s) {
//        userService = s;
//    }

    @Override
    public boolean exists(Post entity) {
        return repository.existsById(entity.getCreated());
    }

    /**
     * This operation is not supported for Post. It always throws an exception.
     *
     * @param id key of the post to be deleted
     * @throws UnsupportedOperationException always
     */
    @Override
    public void deleteById(LocalDateTime id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Attempts to delete a post. Checks whether the caller is the same user as the author of the post.
     *
     * @param id        The post ID that is being deleted.
     * @param actedUpon The user that initiates this action.
     * @throws AccessDeniedException In case the caller is not the author of the post being deleted.
     */
    @SuppressWarnings("unused")
    public void deleteById(LocalDateTime id, User actedUpon) {
        Optional<Post> optionalPost = readById(id);
        if (optionalPost.isEmpty()) // check whether the post exists
            return;
        if (optionalPost.get().getAuthor().equals(actedUpon))
            super.deleteById(id);
        else
            throw new AccessDeniedException("Only author can delete their post");
    }

    /**
     * Add like of specified user to specified post.
     *
     * @param id       key of the post
     * @param username key of the user
     * @throws NullPointerException if the specified post or user do not exist
     */
    @SuppressWarnings("unused")
    public void likePost(LocalDateTime id, String username) {
        //an example of complex business operation
        Optional<Post> optionalPost = readById(id);
        Optional<User> optionalUser = userService.readById(username);

        Post post = optionalPost.orElseThrow(); //throws NullPointerException if the Optional is empty
        User user = optionalUser.orElseThrow();
        post.addLike(user);
        try {
            update(post);
        } catch (EntityStateException e) {
            // this should never happen as we already checked existence of the post
            throw new RuntimeException(e);
        }
    }

    List<Post> findAllLikedByUser(String username) {
        return repository.findAllByLikedUsername(username);
    }
}
