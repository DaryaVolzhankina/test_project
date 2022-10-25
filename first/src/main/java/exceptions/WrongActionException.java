package exceptions;

/**
 * Exception when choosing the wrong action
 */
public class WrongActionException extends RuntimeException {
    public WrongActionException(String message) {
        super(message);
    }
}