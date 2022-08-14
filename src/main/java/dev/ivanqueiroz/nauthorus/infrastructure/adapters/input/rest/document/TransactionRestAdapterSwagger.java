package dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.document;

import dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.request.TransactionCreateRequest;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.response.GetAllUserTransactionsResponse;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.response.TransactionCreateResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.QueryValue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface TransactionRestAdapterSwagger {
    @Operation(description = "Convert between two currencies ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return currency transaction data."),
            @ApiResponse(responseCode = "400", description = "You dont have permission to use this resource."),
            @ApiResponse(responseCode = "500", description = "Internal exception."),
    })
    HttpResponse<TransactionCreateResponse> createTransaction(@Valid TransactionCreateRequest transactionCreateRequest);

    @Operation(description = "Return a list with 0 o more transactions of informed user id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return transaction list of user id"),
            @ApiResponse(responseCode = "400", description = "You dont have permission to use this resource."),
            @ApiResponse(responseCode = "500", description = "Internal exception."),
    })
    HttpResponse<List<GetAllUserTransactionsResponse>> getAllTransactionsByUser(@QueryValue @NotNull Long userId);
}
