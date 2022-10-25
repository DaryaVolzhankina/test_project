package exceptions;

/**
 * Incorrect PIN code exception
 */
public class WrongPinCodeException extends RuntimeException {
    public WrongPinCodeException(String message) {
        super(message);
    }
}