package dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest;

import dev.ivanqueiroz.nauthorus.domain.exception.NotFoundException;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.response.ApiErrorResponse;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.response.ErrorResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.web.router.exceptions.UnsatisfiedQueryValueRouteException;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class ErrorAdviceRestAdapter {

    @Error(exception = NotFoundException.class, global = true)
    public HttpResponse<ApiErrorResponse> handleNotFoundRuleException(NotFoundException ex) {
        log.debug("Handling not found rule.");
        Set<ErrorResponse> errors = Set.of(buildError(ex.getCode(), ex.getMessage()));
        return HttpResponse.status(HttpStatus.NOT_FOUND).body(baseErrorBuilder(HttpStatus.NOT_FOUND, errors));
    }

    @Error(exception = ConstraintViolationException.class, global = true)
    public HttpResponse<ApiErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        log.debug("Handling validation errors.");
        Set<ErrorResponse> errors = ex.getConstraintViolations().stream().map(error -> buildError(formatFieldPath(error), error.getMessage())).collect(Collectors.toSet());
        return HttpResponse.status(HttpStatus.BAD_REQUEST).body(baseErrorBuilder(HttpStatus.BAD_REQUEST, errors));
    }

    @Error(exception = UnsatisfiedQueryValueRouteException.class, global = true)
    public HttpResponse<ApiErrorResponse> handleUnsatisfiedQueryValueRouteException(UnsatisfiedQueryValueRouteException ex) {
        log.debug("Handling not found rule.");
        Set<ErrorResponse> errors = Set.of(buildError(ex.getMessage()));
        return HttpResponse.status(HttpStatus.BAD_REQUEST).body(baseErrorBuilder(HttpStatus.BAD_REQUEST, errors));
    }

    @Error(global = true)
    public HttpResponse<ApiErrorResponse> handleUnknownException(Throwable ex) {
        log.debug("Handling {} exception . ", ex.getMessage(), ex);
        Set<ErrorResponse> errors = Set.of(buildError(ex.getMessage()));
        return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseErrorBuilder(HttpStatus.INTERNAL_SERVER_ERROR, errors));
    }

    private String formatFieldPath(ConstraintViolation<?> error) {
        String path = error.getPropertyPath().toString();
        return path.substring(path.lastIndexOf(".") + 1);
    }

    private ErrorResponse buildError(String field, String message) {
        return ErrorResponse.builder()
                .field(field)
                .message(message)
                .build();
    }

    private ErrorResponse buildError(String message) {
        return buildError(null, message);
    }

    private ApiErrorResponse baseErrorBuilder(HttpStatus httpStatus, Set<ErrorResponse> errorList) {
        return new ApiErrorResponse(new Date(), httpStatus.getCode(), httpStatus.name(), errorList);
    }

}
