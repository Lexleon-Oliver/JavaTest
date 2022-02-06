package com.sigabem.fretecalcapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CepInvalidException extends RuntimeException{
    public CepInvalidException (String message){
        super(message);
    }
}
