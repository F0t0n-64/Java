/*
 * Project Social Network. Created for Java Technology course at Czech Technical University in Prague,
 * Faculty of Information Technology.
 *
 * Author: Ondřej Guth (ondrej.guth@fit.cvut.cz)
 *
 * This code is intended for educational purposes only.
 */

package cz.cvut.fit.tjv.social_network.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Domain type Post. Its primary key is the date and time of creation. This is an abstract supertype of any post.
 */
@Entity
public class Post implements Serializable {
    /**
     * the date and time of creating of this post; the primary key of any Post
     */
/*    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_generator")
    @SequenceGenerator(name = "post_id_generator", sequenceName = "post_seq", allocationSize = 1)*/
    @Id
    private LocalDateTime created = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "author_username", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "original_created")
    private Post original;
    /**
     * The contents of this post. It cannot be null.
     */
    @Column(nullable = false)
    protected String contents;

    /**
     * The users that liked this post. Each user can give like to this post only once.
     */
    @ManyToMany
    @JoinTable(name = "user_post_like",
               joinColumns = @JoinColumn(name = "user_username"),
               inverseJoinColumns = @JoinColumn(name = "post_created")
    )
    private final Set<User> liked = new HashSet<>();

    /**
     * Initialize all fields of the new instance.
     *
     * @param original the post that this one replies to; can be null if this post does not reply to anything
     * @param author   the author of this post; cannot be null
     * @throws NullPointerException if the author is null
     */
    protected Post(Post original, User author, String contents) {
        this.original = original;
        this.author = Objects.requireNonNull(author);
        this.contents = Objects.requireNonNull(contents);
    }

    public Post() {

    }

    @SuppressWarnings("unused")
    public Post getOriginal() {
        return original;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    /**
     * Return the collection of all users that like this post.
     *
     * @return the read-only collection of all users that like this post
     */
    @SuppressWarnings("unused")
    public Collection<User> getLiked() {
        return Collections.unmodifiableCollection(liked);
    }

    /**
     * Add given user among those who give like to this post. It is a no-op if the user already likes this post.
     *
     * @param user the user to add
     * @throws NullPointerException if the user is null
     */
    public void addLike(User user) {
        liked.add(Objects.requireNonNull(user));
    }

    public String getContents() {
        return contents;
    }

    /**
     * Compare this and other instance by the time of creating.
     *
     * @param o the other instance to compare
     * @return true if the other instance is a Post and has exactly the same date and time of creating
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        return getCreated().equals(post.getCreated());
    }

    @Override
    public int hashCode() {
        return getCreated().hashCode();
    }

    @Override
    public String toString() {
        return "Post{" +
                "reply to=" + original +
                ", author=" + author +
                ", created=" + created +
                ", liked=" + liked +
                '}';
    }
}
