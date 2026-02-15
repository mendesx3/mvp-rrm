package com.mvp.adapters.api.exception;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;
import java.util.List;

@Schema(description = "Resposta padrão de erro")
public record ErrorResponse(
        @Schema(description = "Mensagem amigável") String mensagem,
        @Schema(description = "Código do erro") String codigo,
        @Schema(description = "Momento do erro") OffsetDateTime timestamp,
        @Schema(description = "Detalhes de validação") List<String> detalhes
) {
}
