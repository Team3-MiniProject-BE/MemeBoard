package com.example.team3_miniproject.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MessageResponseDto {
    private String msg;
    private HttpStatus statusCode;

    public MessageResponseDto(String msg, HttpStatus statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
