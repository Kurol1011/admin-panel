package kz.management.adminpanel.exception;

public class UserIsAlreadyExistsException extends RuntimeException{
    public UserIsAlreadyExistsException(String message) {
        super(message);
    }
}
