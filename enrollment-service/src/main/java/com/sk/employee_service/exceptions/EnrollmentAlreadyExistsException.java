package com.sk.employee_service.exceptions;

import com.sk.employee_service.entity.Enrollment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class EnrollmentAlreadyExistsException extends RuntimeException{
    public EnrollmentAlreadyExistsException(String message){
        super(message);
    }
}
