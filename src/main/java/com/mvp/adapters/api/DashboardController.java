package com.mvp.adapters.api;

import com.mvp.adapters.api.dto.DailySummaryDTO;
import com.mvp.core.usecase.GetDailySummaryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/dashboard")
@Tag(name = "Dashboard", description = "Indicadores diários do negócio")
public class DashboardController {
    private final GetDailySummaryUseCase getDailySummaryUseCase;

    public DashboardController(GetDailySummaryUseCase getDailySummaryUseCase) {
        this.getDailySummaryUseCase = getDailySummaryUseCase;
    }

    @GetMapping("/daily-summary")
    @Operation(summary = "Obtém resumo diário", description = "Retorna indicadores diários de vendas, contas e caixa")
    @ApiResponse(responseCode = "200", description = "Resumo diário calculado",
            content = @Content(schema = @Schema(implementation = DailySummaryDTO.class)))
    public DailySummaryDTO dailySummary(
            @RequestParam(value = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        LocalDate targetDate = date == null ? LocalDate.now() : date;
        GetDailySummaryUseCase.DailySummaryResult result = getDailySummaryUseCase.execute(targetDate);

        return new DailySummaryDTO(
                result.totalSales(),
                result.totalToReceive(),
                result.totalToPay(),
                result.netCashFlow()
        );
    }
}
