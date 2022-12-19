package com.example.team3_miniproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class MessageResponseDto {
    private String msg;
    private HttpStatus statusCode;

    public MessageResponseDto(String msg, HttpStatus statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }

    public MessageResponseDto(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
