package com.mvp.adapters.api;

import com.mvp.adapters.api.dto.ProcessSaleRequest;
import com.mvp.adapters.api.dto.SaleItemResponse;
import com.mvp.adapters.api.dto.SaleResponse;
import com.mvp.core.domain.Sale;
import com.mvp.core.usecase.ProcessSaleUseCase;
import com.mvp.infrastructure.service.ProcessSaleTransactionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
@Tag(name = "Vendas", description = "Operações do fluxo de venda profissional")
public class SaleController {
    private final ProcessSaleTransactionalService processSaleService;

    public SaleController(ProcessSaleTransactionalService processSaleService) {
        this.processSaleService = processSaleService;
    }

    @PostMapping
    @Operation(summary = "Processa uma venda", description = "Executa o fluxo completo de venda com atualização de estoque e financeiro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda processada com sucesso",
                    content = @Content(schema = @Schema(implementation = SaleResponse.class)))
    })
    public SaleResponse create(@Valid @RequestBody ProcessSaleRequest request) {
        Sale sale = processSaleService.execute(
                request.contactId(),
                request.paymentMethod(),
                request.items().stream().map(i -> new ProcessSaleUseCase.SaleItemInput(i.productId(), i.quantity())).toList()
        );

        return new SaleResponse(
                sale.getId(),
                sale.getContact().getId(),
                sale.getItems().stream()
                        .map(i -> new SaleItemResponse(i.getProduct().getId(), i.getQuantity(), i.getUnitPrice(), i.getTotalItemValue()))
                        .toList(),
                sale.getTotalValue(),
                sale.getPaymentMethod(),
                sale.getCreatedAt()
        );
    }
}
