package dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.persistence;

import dev.ivanqueiroz.nauthorus.application.ports.output.TransactionOutputPort;
import dev.ivanqueiroz.nauthorus.domain.model.Transaction;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.persistence.entity.TransactionEntity;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.persistence.mapper.TransactionPersistenceMapper;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.persistence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TransactionPersistenceAdapter implements TransactionOutputPort {

    private final TransactionRepository transactionRepository;

    private final TransactionPersistenceMapper transactionPersistenceMapper;

    @Override
    public Transaction save(Transaction transaction) {
        var transactionEntity = transactionPersistenceMapper.toTransactionEntity(transaction);
        var savedTransaction = transactionRepository.save(transactionEntity);
        return transactionPersistenceMapper.toTransaction(savedTransaction);
    }

    @Override
    public Optional<List<Transaction>> findAllTransactionsByUserId(Long userId) {
        List<TransactionEntity> transactionEntityList = transactionRepository.findByUserId(userId);
        if (transactionEntityList.isEmpty()) {
            return Optional.empty();
        }
        List<Transaction> transactions = transactionPersistenceMapper.toTransactionList(transactionEntityList);
        return Optional.of(transactions);
    }
}
