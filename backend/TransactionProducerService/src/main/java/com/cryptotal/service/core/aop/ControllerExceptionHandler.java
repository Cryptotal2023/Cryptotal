package com.cryptotal.service.core.aop;

import com.cryptotal.service.core.domain.response.GeneralResponse;
import com.cryptotal.service.core.exception.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    /*@ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleException(Exception e){
        return new ResponseEntity(ErrorResponse.buildErrorResponse(e.toString()), HttpStatus.OK);
    }*/

    @ExceptionHandler(value = {AccountNotFoundException.class})
    public ResponseEntity handleTokenNotFoundException(Exception e){
        return new ResponseEntity(GeneralResponse.buildErrorResponse(
                e.getMessage()),
                HttpStatus.OK
        );
    }

}
