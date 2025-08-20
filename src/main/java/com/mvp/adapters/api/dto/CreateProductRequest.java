package com.mvp.adapters.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Dados para criação de um novo produto")
public record CreateProductRequest(
        @Schema(description = "Nome do produto") String name,
        @Schema(description = "Descrição detalhada do produto") String description,
        @Schema(description = "Preço de venda") BigDecimal price,
        @Schema(description = "Identificador da categoria") UUID categoryId,
        @Schema(description = "Quantidade em estoque") int stock
) {}
