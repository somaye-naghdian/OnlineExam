package ir.maktab.exceptions;

public class UserAlreadyExistsException extends UserAuthenticate {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
