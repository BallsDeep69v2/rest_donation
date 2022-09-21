package com.example.rest_donation.presentation;

import com.example.rest_donation.domain.exceptions.NoSuchDonationException;
import com.example.rest_donation.domain.exceptions.NoSuchPersonException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DonationAdvice {

    @ExceptionHandler(NoSuchDonationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNoSuchElementException(NoSuchDonationException e) {
        return new ApiError(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleInvalidArguments(MethodArgumentNotValidException e) {
        return new ApiError(e.getMessage());
    }
}
