package com.finalprojectrest.exceptions;

import com.finalprojectrest.dto.response.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

import static com.finalprojectrest.enums.ErrorCodeEnum.*;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomException(UserNotFoundException e) {
        return ErrorResponse.of(USER_NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInputParam(MethodArgumentTypeMismatchException e) {
        String param = e.getParameter().getParameter().getName();
        return ErrorResponse.of(VALIDATION_ERROR, param + " has invalid type!");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInputParam(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            errors.putIfAbsent(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ErrorResponse.of(VALIDATION_ERROR, errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolation(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();

        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(field, message);
        }

        return ErrorResponse.of(VALIDATION_ERROR, errors);
    }

    @ExceptionHandler(AlreadyRegisteredUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleRepetition(AlreadyRegisteredUserException e) {
        return ErrorResponse.of(ALREADY_REGISTERED_ERROR);
    }

    @ExceptionHandler(TakenEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleTakenEmail(TakenEmailException e) {
        return ErrorResponse.of(TAKEN_EMAIL_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCredentialsIssue(BadCredentialsException e) {
        return ErrorResponse.of(BAD_CREDENTIALS_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(ILLEGAL_ARGUMENT_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(PatternSyntaxException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlePatternError(PatternSyntaxException ex) {
        return ErrorResponse.of(VALIDATION_ERROR, "Invalid regex pattern in validation. Please contact support.");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnknown(Exception e) {
        return ErrorResponse.of(UNKNOWN_ERROR, "Unexpected error occurred: " + e.getMessage());
    }
}
