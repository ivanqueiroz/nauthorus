package dev.ivanqueiroz.nauthorus.application.ports.output;

import dev.ivanqueiroz.nauthorus.domain.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionOutputPort {

    Transaction save(Transaction transaction);

    Optional<List<Transaction>> findAllTransactionsByUserId(Long userId);
}
