package edu.school21.exception;

public class AlreadyAuthenticatedException extends Exception {
    public AlreadyAuthenticatedException(String message) {
        super(message);
    }
}
