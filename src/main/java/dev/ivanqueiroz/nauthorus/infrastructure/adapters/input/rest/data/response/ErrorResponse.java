package dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.response;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String field,
        String message
) {
}
