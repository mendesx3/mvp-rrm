package com.mvp.adapters.api;

import com.mvp.adapters.api.dto.CreateFinancialEntryRequest;
import com.mvp.adapters.api.dto.FinancialTransactionResponse;
import com.mvp.core.domain.FinancialTransaction;
import com.mvp.core.usecase.CreateFinancialEntryUseCase;
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
@RequestMapping("/financial")
@Tag(name = "Financeiro", description = "Operações de contas a pagar e receber")
public class FinancialController {
    private final CreateFinancialEntryUseCase createFinancialEntryUseCase;

    public FinancialController(CreateFinancialEntryUseCase createFinancialEntryUseCase) {
        this.createFinancialEntryUseCase = createFinancialEntryUseCase;
    }

    @PostMapping
    @Operation(summary = "Cria um lançamento financeiro", description = "Registra manualmente um lançamento financeiro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lançamento criado com sucesso",
                    content = @Content(schema = @Schema(implementation = FinancialTransactionResponse.class)))
    })
    public FinancialTransactionResponse create(@Valid @RequestBody CreateFinancialEntryRequest request) {
        FinancialTransaction transaction = createFinancialEntryUseCase.execute(
                request.description(),
                request.value(),
                request.dueDate(),
                request.type(),
                request.contactId()
        );

        return new FinancialTransactionResponse(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getValue(),
                transaction.getDueDate(),
                transaction.getPaymentDate(),
                transaction.getType(),
                transaction.getStatus(),
                transaction.getContact().getId()
        );
    }
}
