package com.miguel.project.specialist.exceptions;

import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public class ExceptionResponse {

    private Instant instant;
    private String message;
    private String details;
    private int code;
}
