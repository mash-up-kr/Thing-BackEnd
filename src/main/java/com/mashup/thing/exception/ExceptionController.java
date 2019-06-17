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
    public ResponseEntity<String> restExceptionHandler(HttpServletRequest req, BaseException exception) throws RuntimeException {
        log.error("Method:{} - RequestUrl:{} - Status:{}", req.getMethod(), req.getRequestURI(), exception.getCode());

        switch (exception.getCode()) {
            case BAD_REQUEST:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘 못된 요청");
            case CONFLICT:
                return ResponseEntity.status(HttpStatus.CONFLICT).body("중복(충돌)");
            default:
                throw new RuntimeException();
        }
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> unhandledExceptionHandler(RuntimeException exception) {
        log.error(exception.getMessage());
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

}
