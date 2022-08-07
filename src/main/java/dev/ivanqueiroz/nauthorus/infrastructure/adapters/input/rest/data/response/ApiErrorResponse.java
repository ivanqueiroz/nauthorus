package dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.response;

import lombok.Builder;

import java.util.Date;
import java.util.Set;

@Builder
public record ApiErrorResponse(
        Date timestamp,
        Integer status,
        String code,
        Set<ErrorResponse> errors
) {
}
