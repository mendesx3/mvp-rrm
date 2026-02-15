package com.mvp.adapters.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Representação de um produto retornado pela API")
public record ProductResponse(
        @Schema(description = "Identificador do produto") UUID id,
        @Schema(description = "Nome do produto") String name,
        @Schema(description = "Descrição do produto") String description,
        @Schema(description = "Preço de custo") BigDecimal costPrice,
        @Schema(description = "Preço de venda") BigDecimal salePrice,
        @Schema(description = "Quantidade em estoque") Integer stockQuantity
) {}
