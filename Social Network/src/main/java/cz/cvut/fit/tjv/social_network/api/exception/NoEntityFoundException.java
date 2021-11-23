package cz.cvut.fit.tjv.social_network.api.exception;

public class NoEntityFoundException extends RuntimeException {

    public NoEntityFoundException() {
        super("No entity found");
    }
}
