package com.example.team3_miniproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    private String username;            // 사용자 Id
    private String password;            // 사용자 Password
}
