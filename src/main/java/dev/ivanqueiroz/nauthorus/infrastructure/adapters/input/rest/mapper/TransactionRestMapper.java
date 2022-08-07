package dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.mapper;

import dev.ivanqueiroz.nauthorus.domain.model.Transaction;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.request.TransactionCreateRequest;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.response.GetAllUserTransactionsResponse;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.response.TransactionCreateResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "jsr330", imports = UUID.class)
public interface TransactionRestMapper {

    @Mapping(target = "transactionId", expression = "java(UUID.randomUUID().toString())")
    TransactionCreateResponse toTransactionCreateResponse(Transaction transaction);

    @Mapping(target = "currencyRate", ignore = true)
    @Mapping(target = "result", ignore = true)
    @Mapping(target = "rateOrigin", ignore = true)
    @Mapping(target = "rateDestiny", ignore = true)
    @Mapping(target = "date", ignore = true)
    Transaction toTransaction(TransactionCreateRequest transaction);

    @IterableMapping(qualifiedByName="mapWithoutData")
    List<GetAllUserTransactionsResponse> toGetAllUserTransactionsResponses(List<Transaction> transactions);

    @Named("mapWithoutData")
    @Mapping(target = "transactionId", ignore = true)
    GetAllUserTransactionsResponse toGetAllUserTransactionsResponse(Transaction transaction);
}
