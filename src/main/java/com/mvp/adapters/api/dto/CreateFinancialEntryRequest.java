package com.mvp.adapters.api.dto;

import com.mvp.core.domain.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "Dados para lançamento financeiro manual")
public record CreateFinancialEntryRequest(
        @NotBlank(message = "Descrição do lançamento é obrigatória")
        @Schema(description = "Descrição do lançamento", example = "Conta de energia") String description,

        @NotNull(message = "Valor do lançamento é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "Valor do lançamento deve ser maior que zero")
        @Schema(description = "Valor do lançamento", example = "420.50") BigDecimal value,

        @Schema(description = "Data de vencimento", example = "2026-02-28") LocalDate dueDate,

        @NotNull(message = "Tipo da transação é obrigatório")
        @Schema(description = "Tipo da transação: ENTRADA ou SAIDA", example = "SAIDA") TransactionType type,

        @NotNull(message = "Contato vinculado é obrigatório")
        @Schema(description = "Identificador do contato vinculado", example = "b6fb10de-f532-4be4-8b0b-c22a7109fe4c") UUID contactId
) {
}
