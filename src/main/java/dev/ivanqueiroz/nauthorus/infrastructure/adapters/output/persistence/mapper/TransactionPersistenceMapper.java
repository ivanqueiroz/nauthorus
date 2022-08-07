package dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.persistence.mapper;

import dev.ivanqueiroz.nauthorus.domain.model.Transaction;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "jsr330")
public interface TransactionPersistenceMapper {


    @Mapping(target = "id", ignore = true)
    TransactionEntity toTransactionEntity(Transaction transaction);

    @Mapping(target = "rateOrigin", ignore = true)
    @Mapping(target = "rateDestiny", ignore = true)
    Transaction toTransaction(TransactionEntity transactionEntity);

    List<Transaction> toTransactionList(List<TransactionEntity> transactionEntityList);

}
