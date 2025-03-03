package com.crown.employee.attendance_register.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException( String message ) {
        super(message);
    }

    public ResourceNotFoundException( String resx, String attr, Long id1 ) {
        super("No matching records found for Resource: " + resx + " with attribute " + attr + " or ID: " + id1);
    }
}
