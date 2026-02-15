package com.mvp.adapters.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Dados para criação de um novo produto")
public record CreateProductRequest(
        @NotBlank(message = "Nome do produto é obrigatório")
        @Schema(description = "Nome do produto", example = "Camiseta") String name,

        @NotBlank(message = "Descrição do produto é obrigatória")
        @Schema(description = "Descrição detalhada do produto", example = "Camiseta básica algodão") String description,

        @NotNull(message = "Preço de custo é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "Preço de custo deve ser maior que zero")
        @Schema(description = "Preço de custo", example = "25.00") BigDecimal costPrice,

        @NotNull(message = "Preço de venda é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "Preço de venda deve ser maior que zero")
        @Schema(description = "Preço de venda", example = "49.90") BigDecimal salePrice,

        @NotNull(message = "Quantidade em estoque é obrigatória")
        @Min(value = 0, message = "Quantidade em estoque não pode ser negativa")
        @Schema(description = "Quantidade em estoque", example = "80") Integer stockQuantity
) {}
