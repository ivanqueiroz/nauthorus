package dev.ivanqueiroz.nauthorus.application.ports.input;

import dev.ivanqueiroz.nauthorus.domain.model.Transaction;

import java.util.List;

public interface GetAllUserTransactionsUseCase {

    List<Transaction> getAll(Long userId);

}
