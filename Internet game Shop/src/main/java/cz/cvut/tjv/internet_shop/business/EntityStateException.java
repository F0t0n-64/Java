package cz.cvut.tjv.internet_shop.business;

public class EntityStateException extends RuntimeException {
    public EntityStateException() {}

    public <E> EntityStateException(E entity) {
        super("Illegal state of entity " + entity);
    }
}
