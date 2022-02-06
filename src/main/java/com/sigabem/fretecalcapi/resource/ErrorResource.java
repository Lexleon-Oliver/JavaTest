package com.sigabem.fretecalcapi.resource;

import com.sigabem.fretecalcapi.dto.response.ErrorMessageResponse;
import com.sigabem.fretecalcapi.exception.CepInvalidException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorResource extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageResponse internalError(Exception ex, WebRequest req){
        return new ErrorMessageResponse(500, ex.getLocalizedMessage(), "Internal Server Error", "Internal Server Error Exception");
    }

    @ExceptionHandler(CepInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse cepInvalidError(Exception ex, WebRequest req){
        return new ErrorMessageResponse(400, ex.getLocalizedMessage(), "Dado de Entrada Incorreto", "Cep Invalid Exception");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(400, ex.getFieldError().getDefaultMessage(), "Argumento Inválido","Invalid Request Exception");
        return handleExceptionInternal(ex, errorMessageResponse, headers, HttpStatus.BAD_REQUEST, request);
    }
}
