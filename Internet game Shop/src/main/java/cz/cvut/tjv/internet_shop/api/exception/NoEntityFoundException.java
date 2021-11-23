package cz.cvut.tjv.internet_shop.api.exception;

public class NoEntityFoundException extends RuntimeException {

    public NoEntityFoundException() {
        super("No entity found");
    }
}
