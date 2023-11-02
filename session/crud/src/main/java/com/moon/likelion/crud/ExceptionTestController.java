package com.moon.likelion.crud;

import com.moon.likelion.crud.exception.BaseException;
import com.moon.likelion.crud.exception.ErrorResponseDto;
import com.moon.likelion.crud.exception.PostNotExistException;
import com.moon.likelion.crud.exception.PostNotInBoardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("except")
public class ExceptionTestController {
    @GetMapping("/{id}")
    public void throwException(@PathVariable int id) {
        switch (id) {
            case 1:
                throw new PostNotExistException();
            case 2:
                throw new PostNotInBoardException();
            default:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

//    @ExceptionHandler(BaseException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponseDto handleBaseException(BaseException exception) {
//        return new ErrorResponseDto(exception.getMessage());
//    }
}
