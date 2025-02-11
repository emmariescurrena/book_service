package com.emmariescurrena.bookesy.book_service.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleSecurityException(Exception exception) {
        ProblemDetail errorDetail;

        exception.printStackTrace();

        if (exception instanceof MethodArgumentNotValidException) {
            List<String> errors = ((MethodArgumentNotValidException) exception).getBindingResult().getFieldErrors()
                    .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), "Validation failed");
            errorDetail.setProperty("errors", errors);
        } else if (exception instanceof NotFoundException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), exception.getMessage());
            errorDetail.setProperty("description", "Resource not found");
        } else {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
            errorDetail.setProperty("description", "Unknown internal server error.");
        }

        return ResponseEntity.status(errorDetail.getStatus()).body(errorDetail);
    }

}