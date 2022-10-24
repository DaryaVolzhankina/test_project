package exceptions;

public class WrongPinCodeException extends RuntimeException {
    public WrongPinCodeException(String message) {
        super(message);
    }
}