package com.mvp.adapters.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Resposta de um pedido gerado a partir do carrinho")
public record OrderResponse(
        @Schema(description = "Identificador do pedido") UUID id,
        @Schema(description = "Valor total do pedido") BigDecimal total
) {}
