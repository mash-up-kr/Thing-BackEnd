package com.mashup.thing.exception;

import com.mashup.thing.exception.dto.ResErrorDto;
import com.mashup.thing.exception.dto.ResExceptionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ResExceptionDto> restExceptionHandler(HttpServletRequest req, BaseException exception) throws RuntimeException {
        log.error("Method:{} - RequestUrl:{} - Status:{} - Msg:{}",
                req.getMethod(),
                req.getRequestURI(),
                exception.getErrorModel().getHttpStatus(),
                exception.getErrorModel().getMassage());

        switch (exception.getErrorModel().getHttpStatus()) {
            case FORBIDDEN:
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(toResExceptionDto(exception.getErrorModel()));
            case BAD_REQUEST:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(toResExceptionDto(exception.getErrorModel()));
            case CONFLICT:
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(toResExceptionDto(exception.getErrorModel()));
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


    private ResExceptionDto toResExceptionDto(ErrorModel errorModel) {
        return ResExceptionDto.builder()
                .error(toResErrorDto(errorModel))
                .build();
    }

    private ResErrorDto toResErrorDto(ErrorModel errorModel) {
        return ResErrorDto.builder()
                .code(errorModel.getCode())
                .massage(errorModel.getMassage())
                .build();
    }

}
