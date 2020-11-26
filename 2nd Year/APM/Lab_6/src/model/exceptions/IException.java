package model.exceptions;

public class IException extends Exception {
    public IException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
