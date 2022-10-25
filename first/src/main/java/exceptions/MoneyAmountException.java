package exceptions;

/**
 * Exception in case of an error with the amount of money
 */
public class MoneyAmountException extends RuntimeException {
    public MoneyAmountException(String message) {
        super(message);
    }
}