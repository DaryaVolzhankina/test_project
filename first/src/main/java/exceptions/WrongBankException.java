package exceptions;

public class WrongBankException extends RuntimeException {
    public WrongBankException(String message) {
        super(message);
    }
}