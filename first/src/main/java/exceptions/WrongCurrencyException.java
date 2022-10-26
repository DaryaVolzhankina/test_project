package exceptions;

/**
 * Exception in case of currency mismatch
 */
public class WrongCurrencyException extends RuntimeException {
    public WrongCurrencyException(String message) {
        super(message);
    }
}