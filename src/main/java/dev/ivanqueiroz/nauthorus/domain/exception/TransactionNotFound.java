package dev.ivanqueiroz.nauthorus.domain.exception;

public class TransactionNotFound extends NotFoundException {
    public TransactionNotFound(String message)  {
        super(message);
    }

    @Override
    public String getCode() {
        return "TR01";
    }
}
