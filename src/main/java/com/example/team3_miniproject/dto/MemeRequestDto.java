package com.example.team3_miniproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemeRequestDto {
    private Long id;

    private String username;
    private String nickname;
    private String password;

    private String title;
    private String img;
    private int answerValue;
    private String exam1;
    private String exam2;
    private String exam3;

}
