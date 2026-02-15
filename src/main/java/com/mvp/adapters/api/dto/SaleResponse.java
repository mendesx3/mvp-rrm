package com.mvp.adapters.api.dto;

import com.mvp.core.domain.PaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Schema(description = "Representação de uma venda processada")
public record SaleResponse(
        @Schema(description = "Identificador da venda") UUID id,
        @Schema(description = "Identificador do cliente") UUID contactId,
        @Schema(description = "Itens") List<SaleItemResponse> items,
        @Schema(description = "Valor total da venda") BigDecimal totalValue,
        @Schema(description = "Forma de pagamento") PaymentMethod paymentMethod,
        @Schema(description = "Data/hora de criação") LocalDateTime createdAt
) {
}
