package com.example.rest_donation.domain.exceptions;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoSuchDonationException extends ResponseStatusException {
    public NoSuchDonationException() {
        super(HttpStatus.NOT_FOUND);
    }
}
