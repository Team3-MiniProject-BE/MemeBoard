package com.example.team3_miniproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequestDto {
    private String username;
    private String password;
    private String nickname;
    private boolean admin = false;
    private String adminToken = "";
}
