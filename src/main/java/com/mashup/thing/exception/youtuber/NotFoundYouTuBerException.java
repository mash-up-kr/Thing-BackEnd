package com.mashup.thing.exception.youtuber;

import com.mashup.thing.exception.BaseException;
import com.mashup.thing.exception.ErrorModel;
import org.springframework.http.HttpStatus;

public class NotFoundYouTuBerException extends BaseException {
    public NotFoundYouTuBerException() {
        this(HttpStatus.BAD_REQUEST);
    }

    public NotFoundYouTuBerException(HttpStatus httpStatus) {
        this(4003, httpStatus);
    }

    public NotFoundYouTuBerException(int code, HttpStatus httpStatus) {
        super(ErrorModel.builder()
                .code(code)
                .httpStatus(httpStatus)
                .massage("Not Found YouTuber - Invalid YouTuberId")
                .build());
    }

}
