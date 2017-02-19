package io.dods.handler;

import io.dods.services.misc.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionHandlingController {

    @Autowired
    LoggerService loggerService;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RuntimeException.class)
    public RuntimeException handleBaseException(RuntimeException e){
        loggerService.error(getClass(), e);
        return e;
    }

}