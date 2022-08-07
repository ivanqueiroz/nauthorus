package dev.ivanqueiroz.nauthorus.domain.exception;

public abstract class NotFoundException extends RuntimeException {

    protected NotFoundException(String message) {
        super(message);
    }

    public abstract String getCode();
}
