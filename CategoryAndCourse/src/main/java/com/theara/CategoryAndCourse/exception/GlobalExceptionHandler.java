package com.theara.CategoryAndCourse.exception;

import com.theara.CategoryAndCourse.constant.ResponseDTO;
import com.theara.CategoryAndCourse.constant.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ExceptionNotFound.class)
    public ResponseEntity<ResponseDTO> handleExceptionNotFound(ExceptionNotFound e) {
        ResponseDTO response = new ResponseDTO(e.getMessage(), Status.FAILED.value(), e.getStatusCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getStatusCode()));
    }
}
