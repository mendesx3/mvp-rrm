package com.mvp.adapters.api.dto;

import com.mvp.core.domain.TransactionStatus;
import com.mvp.core.domain.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "Representação de um lançamento financeiro")
public record FinancialTransactionResponse(
        @Schema(description = "Identificador do lançamento") UUID id,
        @Schema(description = "Descrição") String description,
        @Schema(description = "Valor") BigDecimal value,
        @Schema(description = "Data de vencimento") LocalDate dueDate,
        @Schema(description = "Data de pagamento") LocalDate paymentDate,
        @Schema(description = "Tipo da transação") TransactionType type,
        @Schema(description = "Status da transação") TransactionStatus status,
        @Schema(description = "Identificador do contato") UUID contactId
) {
}
