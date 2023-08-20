package com.hussien.stcassessment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AccessDeniedException extends ResponseStatusException {
    public AccessDeniedException() {
        super(HttpStatus.FORBIDDEN, "Access Denied");
    }
}
