package com.example.team3_miniproject.dto;

import org.springframework.http.HttpStatus;

public class MessageResponseDto {
    private HttpStatus statusCode;
    private String msg;

    public MessageResponseDto(HttpStatus statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
