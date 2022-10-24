package exceptions;

public class WrongCurrencyException extends RuntimeException {
    public WrongCurrencyException(String message) {
        super(message);
    }
}