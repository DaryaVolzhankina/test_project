package exceptions;

public class WrongActionException extends RuntimeException {
    public WrongActionException(String message) {
        super(message);
    }
}