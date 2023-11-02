package com.moon.likelion.crud.handler;

import com.moon.likelion.crud.exception.BaseException;
import com.moon.likelion.crud.exception.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class PostControllerAdvice {
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handlerException(BaseException exception) {
        return new ErrorResponseDto(exception.getMessage());
    }
}
