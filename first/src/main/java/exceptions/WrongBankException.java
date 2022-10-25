package exceptions;

/**
 * Exception in case of mismatch of banks
 */
public class WrongBankException extends RuntimeException {
    public WrongBankException(String message) {
        super(message);
    }
}