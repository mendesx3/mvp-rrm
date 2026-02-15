package com.mvp.adapters.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Resumo diário do negócio")
public record DailySummaryDTO(
        @Schema(description = "Total de vendas do dia", example = "4290.50") BigDecimal totalSales,
        @Schema(description = "Total a receber (entradas pendentes)", example = "1200.00") BigDecimal totalToReceive,
        @Schema(description = "Total a pagar (saídas pendentes)", example = "3500.00") BigDecimal totalToPay,
        @Schema(description = "Saldo de caixa do dia", example = "790.50") BigDecimal netCashFlow
) {
}
