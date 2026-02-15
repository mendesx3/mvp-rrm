package com.mvp.adapters.api.dto;

import com.mvp.core.domain.PaymentMethod;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Schema(description = "Dados para processar uma venda")
public record ProcessSaleRequest(
        @NotNull(message = "Cliente da venda é obrigatório")
        @Schema(description = "Identificador do cliente", example = "a5f10f0e-bf21-4729-bf98-c7b4f3e0708e") UUID contactId,

        @NotNull(message = "Forma de pagamento é obrigatória")
        @Schema(description = "Forma de pagamento", example = "PIX") PaymentMethod paymentMethod,

        @NotEmpty(message = "A venda deve possuir ao menos um item")
        @Valid
        @ArraySchema(schema = @Schema(implementation = ProcessSaleItemRequest.class))
        @Schema(description = "Itens da venda") List<ProcessSaleItemRequest> items
) {
}
