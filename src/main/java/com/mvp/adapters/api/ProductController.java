package com.mvp.adapters.api;

import com.mvp.adapters.api.dto.CreateProductRequest;
import com.mvp.adapters.api.dto.ProductResponse;
import com.mvp.core.domain.Product;
import com.mvp.core.usecase.CreateProduct;
import com.mvp.core.usecase.SearchProducts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Produtos", description = "Operações de gerenciamento de produtos")
public class ProductController {
    private final CreateProduct createProduct;
    private final SearchProducts searchProducts;

    public ProductController(CreateProduct createProduct, SearchProducts searchProducts) {
        this.createProduct = createProduct;
        this.searchProducts = searchProducts;
    }

    @PostMapping
    @Operation(summary = "Cria um novo produto", description = "Adiciona um produto ao catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto criado com sucesso",
                    content = @Content(schema = @Schema(implementation = ProductResponse.class)))
    })
    public ProductResponse create(@Valid @RequestBody CreateProductRequest request) {
        Product product = createProduct.execute(
                request.name(),
                request.description(),
                request.costPrice(),
                request.salePrice(),
                request.stockQuantity()
        );
        return toResponse(product);
    }

    @GetMapping
    @Operation(summary = "Busca produtos", description = "Pesquisa produtos pelo nome ou descrição")
    @ApiResponse(responseCode = "200", description = "Lista de produtos",
            content = @Content(schema = @Schema(implementation = ProductResponse.class)))
    public List<ProductResponse> search(@RequestParam("q") String query) {
        return searchProducts.execute(query).stream().map(this::toResponse).toList();
    }

    private ProductResponse toResponse(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getCostPrice(),
                p.getSalePrice(),
                p.getStockQuantity()
        );
    }
}
