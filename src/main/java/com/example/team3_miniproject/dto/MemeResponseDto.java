package com.example.team3_miniproject.dto;

import com.example.team3_miniproject.entity.MemeBoard;
import lombok.Getter;

@Getter
public class MemeResponseDto {
    private String username;
    private String title;
    private int answerValue;
    private String exam1;
    private String exam2;
    private String exam3;

    public MemeResponseDto(MemeBoard meme) {
        this.username = meme.getUsername();
        this.title = meme.getTitle();
        this.answerValue = meme.getAnswerValue();
        this.exam1 = meme.getExam1();
        this.exam2 = meme.getExam2();
        this.exam3 = meme.getExam3();
    }
}
