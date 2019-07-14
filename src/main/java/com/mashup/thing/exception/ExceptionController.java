package com.mashup.thing.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ErrorModel> restExceptionHandler(HttpServletRequest req, BaseException exception) throws RuntimeException {
        log.error("Method:{} - RequestUrl:{} - Status:{} - Msg:{}",
                req.getMethod(),
                req.getRequestURI(),
                exception.getErrorModel().getHttpStatus(),
                exception.getErrorModel().getMsg());

        switch (exception.getErrorModel().getHttpStatus()) {
            case FORBIDDEN:
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getErrorModel());
            case BAD_REQUEST:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getErrorModel());
            case CONFLICT:
                return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getErrorModel());
            default:
                throw new RuntimeException();
        }
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Exception> unhandledExceptionHandler(RuntimeException exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
    }

}
