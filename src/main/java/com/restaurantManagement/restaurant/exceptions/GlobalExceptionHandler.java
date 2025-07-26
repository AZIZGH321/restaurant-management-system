package com.restaurantManagement.restaurant.exceptions;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.restaurantManagement.restaurant.errors.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.security.SignatureException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception exception){
        ProblemDetail errorDetail = null;

        exception.printStackTrace();

        if(exception instanceof BadCredentialsException){

            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401),exception.getMessage());
            errorDetail.setProperty("description","The username or password are incorrect");

            return errorDetail;
        }
        if(exception instanceof AccountStatusException){

            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403),exception.getMessage());

            errorDetail.setProperty("description","The account is locked");
        }

        if(exception instanceof AccessDeniedException){

            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403),exception.getMessage());
            errorDetail.setProperty("description","You are not authorized to access this resource");
        }

        if(exception instanceof SignatureException){
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403),exception.getMessage());

            errorDetail.setProperty("description","The JWT signature is invalid");
        }

        if(exception instanceof ExpiredJwtException){

            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403),exception.getMessage());

            errorDetail.setProperty("description","The JWT token has expired");
        }

        if(errorDetail == null){
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500),exception.getMessage());

            errorDetail.setProperty("description","Unkown internal server error.");
        }

        return errorDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception){

        Map<String,String> fieldErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage(),
                        (existing,replacement)-> existing
                ));

        ErrorResponse errorResponse = new ErrorResponse(
                "validation failed",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                fieldErrors
        );

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);


    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEnumValue(HttpMessageNotReadableException exception){

        Throwable cause = exception.getCause();

        if(cause instanceof InvalidFormatException formatException){

            String fieldName = null;

            if(!formatException.getPath().isEmpty()){
                fieldName = formatException.getPath().get(0).getFieldName();
            }

            String invalidValue = formatException.getValue().toString();
            String targetType = formatException.getTargetType().getSimpleName();

            String message = String.format("Invalid value '%s' for field '%s'.",
                    invalidValue,
                    fieldName
            );


            ErrorResponse errorResponse = new ErrorResponse(message,HttpStatus.BAD_REQUEST.value(),
                    LocalDateTime.now());


            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

        }

        ErrorResponse errorResponse = new ErrorResponse("invalid request format",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now());


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
