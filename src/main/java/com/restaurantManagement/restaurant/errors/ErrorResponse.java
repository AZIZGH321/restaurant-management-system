package com.restaurantManagement.restaurant.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ErrorResponse {

    private String message;

    private Integer status;

    private LocalDateTime timestamp;

    private Map<String,String> fieldErrors;

    public ErrorResponse(String message,Integer status,LocalDateTime timestamp){

        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    public ErrorResponse(String message,Integer status,LocalDateTime timestamp,Map<String,String> fieldsError){
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
        this.fieldErrors = fieldsError;
    }

}
