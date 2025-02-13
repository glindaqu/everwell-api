package ru.glindaquint.everwell.exceptions.auth;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException() {
        super("Token was expired");
    }
}
