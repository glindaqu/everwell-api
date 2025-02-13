package ru.glindaquint.everwell.exceptions.auth;

public class BadEmailException extends RuntimeException {
    public BadEmailException() {
        super("User with given email already exists");
    }
}
