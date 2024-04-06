package com.ducph.newtest.handler;

import com.ducph.newtest.dto.NotFoundException;
import com.ducph.newtest.dto.RestErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleNotFoundException(NotFoundException e) {
        var status = HttpStatus.NOT_FOUND;
        var error = new RestErrorResponse(status, e.getMessage());
        log.error("Resource not found", e);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleBadRequestException(MethodArgumentNotValidException e) {
        var status = HttpStatus.BAD_REQUEST;
        var errorMessage = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errorMessage.append(String.format("[%s]: %s, ", error.getField(), error.getDefaultMessage()))
        );
        var error = new RestErrorResponse(status, errorMessage.substring(0, errorMessage.toString().length() - 2));
        log.error("Resource not found", e);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleUnknownException(Exception e) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var error = new RestErrorResponse(status, e.getMessage());
        log.error("System error", e);
        return new ResponseEntity<>(error, status);
    }
}
