package com.example.team3_miniproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class MessageResponseDto {
    private String msg;                 // 상태 Msg
    private HttpStatus statusCode;      // 상태 Code
    private MemeResponseDto memeResponseDto;    // ResponseDto


    public MessageResponseDto(String msg, HttpStatus statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }

    public MessageResponseDto(String msg, HttpStatus statusCode, MemeResponseDto memeResponseDto) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.memeResponseDto = memeResponseDto;
    }

    public MessageResponseDto(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
