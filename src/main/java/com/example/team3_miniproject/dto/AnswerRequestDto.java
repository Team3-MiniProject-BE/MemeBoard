package com.example.team3_miniproject.dto;

import com.example.team3_miniproject.entity.Answer;
import com.example.team3_miniproject.entity.MemeBoard;
import com.example.team3_miniproject.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerRequestDto {

    private int answerValue;

    private MemeBoard memeBoard;

    private User user;

    public Answer toEntity(User user, MemeBoard memeBoard) {
        return Answer.builder()
                .answerValue(answerValue)
                .memeBoard(memeBoard)
                .user(user)
                .build();
    }
}
