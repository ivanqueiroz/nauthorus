package dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest;

import dev.ivanqueiroz.nauthorus.application.ports.input.CreateTransactionUseCase;
import dev.ivanqueiroz.nauthorus.application.ports.input.GetAllUserTransactionsUseCase;
import dev.ivanqueiroz.nauthorus.domain.model.Transaction;
import dev.ivanqueiroz.nauthorus.domain.service.TransactionService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.specification.RequestSpecification;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
class TransactionRestAdapterTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    CreateTransactionUseCase createTransactionUseCase;

    @Inject
    GetAllUserTransactionsUseCase getAllUserTransactionsUseCase;

    @MockBean(TransactionService.class)
    CreateTransactionUseCase transactionRestAdapter() {
        return mock(CreateTransactionUseCase.class);
    }

    @MockBean(TransactionService.class)
    GetAllUserTransactionsUseCase getAllUserTransactionsUseCase() {
        return mock(GetAllUserTransactionsUseCase.class);
    }

    @Test
    void testCreateTransaction(RequestSpecification spec) {
        String url = "/currency/convert?amount=3&userId=1&currencyOrigin=USD&currencyDestiny=BRL";
        Transaction mockedTransaction = createMockedTransaction();
        when(createTransactionUseCase.createTransaction(any())).thenReturn(mockedTransaction);
        spec.when()
                .get(url)
                .then()
                .statusCode(HttpStatus.OK.getCode())
                .body("userId", equalTo(1))
                .body("currencyOrigin", equalTo(mockedTransaction.getCurrencyOrigin()))
                .body("currencyDestiny", equalTo(mockedTransaction.getCurrencyDestiny()))
                .body("amount", equalTo(3.0F));
    }

    @Test
    void testCreateTransactionBadRequest(RequestSpecification spec) {
        String url = "/currency/convert?userId=1&currencyOrigin=USD&currencyDestiny=BRL";
        Transaction mockedTransaction = createMockedTransaction();
        when(createTransactionUseCase.createTransaction(any())).thenReturn(mockedTransaction);
        spec.when()
                .get(url)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.getCode())
                .body("status", equalTo(400))
                .body("code", equalTo(HttpStatus.BAD_REQUEST.toString()))
                .body("errors[0].field", equalTo("amount"));
    }

    @Test
    void testGetAllTransactionsByUser(RequestSpecification spec) {
        String url = "/currency/transactions?userId=1";
        var mockedTransaction = createMockedTransaction();
        var transactions = List.of(mockedTransaction);
        when(getAllUserTransactionsUseCase.getAll(any())).thenReturn(transactions);

        spec.when()
                .get(url)
                .then()
                .statusCode(HttpStatus.OK.getCode())
                .body("[0].userId", equalTo(1))
                .body("[0].currencyOrigin", equalTo(mockedTransaction.getCurrencyOrigin()))
                .body("[0].currencyDestiny", equalTo(mockedTransaction.getCurrencyDestiny()))
                .body("[0].amount", equalTo(3.0F));
    }

    @Test
    void testGetAllTransactionsByUserBadRequest(RequestSpecification spec) {
        String url = "/currency/transactions";
        var mockedTransaction = createMockedTransaction();
        var transactions = List.of(mockedTransaction);
        when(getAllUserTransactionsUseCase.getAll(any())).thenReturn(transactions);
        spec.when()
                .get(url)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.getCode())
                .body("status", equalTo(400))
                .body("code", equalTo(HttpStatus.BAD_REQUEST.toString()))
                .body("errors[0].message", equalTo("Required QueryValue [userId] not specified"));
    }

    private Transaction createMockedTransaction() {
        return Transaction.builder()
                .userId(1L)
                .currencyOrigin("USD")
                .currencyDestiny("BRL")
                .rateOrigin(new BigDecimal("6.201951"))
                .result(new BigDecimal("15.492790"))
                .amount(new BigDecimal("3.0"))
                .currencyRate(new BigDecimal("5.164264"))
                .date(Date.from(ZonedDateTime.now(ZoneId.of("UTC")).toInstant()))
                .build();
    }
}