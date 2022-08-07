package dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest;

import dev.ivanqueiroz.nauthorus.application.ports.input.CreateTransactionUseCase;
import dev.ivanqueiroz.nauthorus.application.ports.input.GetAllUserTransactionsUseCase;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.request.TransactionCreateRequest;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.response.GetAllUserTransactionsResponse;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.response.TransactionCreateResponse;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.mapper.TransactionRestMapper;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.validation.Validated;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@Controller("/currency")
@RequiredArgsConstructor
public class TransactionRestAdapter {

    private final CreateTransactionUseCase createTransactionUseCase;

    private final GetAllUserTransactionsUseCase getAllUserTransactionsUseCase;

    private final TransactionRestMapper transactionRestMapper;

    @Get(value = "/convert{?transactionCreateRequest*}")
    public HttpResponse<TransactionCreateResponse> createTransaction(@Valid TransactionCreateRequest transactionCreateRequest) {
        var transaction = transactionRestMapper.toTransaction(transactionCreateRequest);
        var savedTransaction = createTransactionUseCase.createTransaction(transaction);
        var response = transactionRestMapper.toTransactionCreateResponse(savedTransaction);
        return HttpResponse.status(HttpStatus.OK).body(response);
    }

    @Get("/transactions")
    public HttpResponse<List<GetAllUserTransactionsResponse>> getAllTransactionsByUser(@QueryValue @NotNull Long userId) {
        var transactions = getAllUserTransactionsUseCase.getAll(userId);
        var transactionResponseList = transactionRestMapper.toGetAllUserTransactionsResponses(transactions);
        return HttpResponse.status(HttpStatus.OK).body(transactionResponseList);
    }
}
