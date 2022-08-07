package dev.ivanqueiroz.nauthorus.domain.service;

import dev.ivanqueiroz.nauthorus.application.ports.input.CreateTransactionUseCase;
import dev.ivanqueiroz.nauthorus.application.ports.input.GetAllUserTransactionsUseCase;
import dev.ivanqueiroz.nauthorus.application.ports.output.LoadRatesOutputPort;
import dev.ivanqueiroz.nauthorus.application.ports.output.TransactionOutputPort;
import dev.ivanqueiroz.nauthorus.domain.exception.RatesNotFoundException;
import dev.ivanqueiroz.nauthorus.domain.exception.TransactionNotFound;
import dev.ivanqueiroz.nauthorus.domain.model.Transaction;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TransactionService implements CreateTransactionUseCase, GetAllUserTransactionsUseCase {

    private final TransactionOutputPort transactionOutputPort;

    private final LoadRatesOutputPort loadRatesPort;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        var rates = loadRatesPort.load(transaction.getUserId());
        transaction.calculateWithRates(rates
                .orElseThrow(() -> new RatesNotFoundException("Rates not found, please try later.")));
        return transactionOutputPort.save(transaction);
    }

    @Override
    public List<Transaction> getAll(Long userId) {
        return transactionOutputPort.findAllTransactionsByUserId(userId)
                .orElseThrow(() -> new TransactionNotFound("Transactions not found for user id %s".formatted(userId)));
    }
}
