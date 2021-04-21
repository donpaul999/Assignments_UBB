package exceptions;

public class CommandServiceException extends RuntimeException {
    public CommandServiceException(String message) {
        super(message);
    }
}
