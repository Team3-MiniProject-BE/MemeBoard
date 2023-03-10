package com.example.team3_miniproject.dto;

import com.example.team3_miniproject.entity.MemeBoard;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public MemeBoard toEntity() {
        return MemeBoard.builder()
                .username(username)
                .nickname(nickname)
                .password(password)
                .title(title)
                .answerValue(answerValue)
                .exam1(exam1)
                .exam2(exam2)
                .exam3(exam3)
                .build();
    }
}
