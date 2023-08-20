package com.hussien.stcassessment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ItemNotFoundException extends ResponseStatusException {
    public ItemNotFoundException(String itemName) {
        super(HttpStatus.NOT_FOUND, String.format("%s not found", itemName));
    }
}
