package com.javaweb.ControllerAdvice;

import com.javaweb.DTO.ErrorDTO;
import com.javaweb.customException.RequiredFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = RequiredFieldException.class)
    public ResponseEntity<Object> handleRequiredFieldException (RequiredFieldException ex){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError(ex.getMessage());
        errorDTO.setDetails(ex.getErrorDetails());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
