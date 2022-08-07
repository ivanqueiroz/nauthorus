package dev.ivanqueiroz.nauthorus.application.ports.input;

import dev.ivanqueiroz.nauthorus.domain.model.Transaction;

public interface CreateTransactionUseCase {

    Transaction createTransaction(Transaction transaction);

}
