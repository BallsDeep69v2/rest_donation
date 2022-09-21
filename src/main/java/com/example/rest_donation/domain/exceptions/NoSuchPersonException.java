package com.example.rest_donation.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSuchPersonException extends ResponseStatusException {
    public NoSuchPersonException() {
        super(HttpStatus.NOT_FOUND);
    }
}
