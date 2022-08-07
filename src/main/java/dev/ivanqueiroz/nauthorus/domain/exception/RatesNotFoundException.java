package dev.ivanqueiroz.nauthorus.domain.exception;

public class RatesNotFoundException extends NotFoundException {

    public RatesNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return "RR01";
    }
}
