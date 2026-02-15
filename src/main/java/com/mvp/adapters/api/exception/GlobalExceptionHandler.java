package com.mvp.adapters.api.exception;

import com.mvp.core.domain.exception.BusinessException;
import com.mvp.core.domain.exception.InsufficientStockException;
import com.mvp.core.domain.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, "RECURSO_NAO_ENCONTRADO", ex.getMessage(), List.of());
    }

    @ExceptionHandler({BusinessException.class, InsufficientStockException.class})
    public ResponseEntity<ErrorResponse> handleBusiness(RuntimeException ex) {
        return build(HttpStatus.BAD_REQUEST, "REGRA_DE_NEGOCIO", ex.getMessage(), List.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        List<String> detalhes = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    if (error instanceof FieldError fieldError) {
                        return fieldError.getField() + ": " + error.getDefaultMessage();
                    }
                    return error.getDefaultMessage();
                })
                .toList();

        return build(HttpStatus.BAD_REQUEST, "VALIDACAO", "Erro de validação nos dados enviados", detalhes);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "ERRO_INTERNO", "Ocorreu um erro inesperado no sistema", List.of());
    }

    private ResponseEntity<ErrorResponse> build(HttpStatus status, String codigo, String mensagem, List<String> detalhes) {
        return ResponseEntity.status(status).body(new ErrorResponse(mensagem, codigo, OffsetDateTime.now(), detalhes));
    }
}
