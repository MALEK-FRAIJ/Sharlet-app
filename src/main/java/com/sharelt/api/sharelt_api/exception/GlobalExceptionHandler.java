package com.sharelt.api.sharelt_api.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(BadRequestException.class)
        public ResponseEntity<ErrorDetails> badRequestExceptionHandler(BadRequestException ex,
                        WebRequest request) {
                ErrorDetails errorDetails = new ErrorDetails(request.getClass().getName(), List.of(ex.getMessage()),
                                request.getDescription(false), LocalDateTime.now());
                return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(AuthenticationException.class)
        public ResponseEntity<ErrorDetails> authenticationExceptionHandler(AuthenticationException ex,
                        WebRequest request) {
                ErrorDetails errorDetails = new ErrorDetails(request.getClass().getName(), List.of(ex.getMessage()),
                                request.getDescription(false), LocalDateTime.now());
                return new ResponseEntity<>(errorDetails, HttpStatus.FOUND);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex,
                        WebRequest request) {
                List<String> errorMessages = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(FieldError::getDefaultMessage)
                                .collect(Collectors.toList());
                ErrorDetails errorDetails = new ErrorDetails("Validation Error", errorMessages,
                                request.getDescription(false),
                                LocalDateTime.now());
                return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(UserException.class)
        public ResponseEntity<ErrorDetails> userExceptionHandler(UserException ex, WebRequest request) {
                ErrorDetails errorDetails = new ErrorDetails(request.getClass().getName(), List.of(ex.getMessage()),
                                request.getDescription(false), LocalDateTime.now());
                return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception ex, WebRequest request) {
                ErrorDetails errorDetails = new ErrorDetails("Unexpected error occurred", null,
                                request.getDescription(false),
                                LocalDateTime.now());
                return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
