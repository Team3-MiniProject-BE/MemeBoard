package com.example.team3_miniproject.dto;

import com.example.team3_miniproject.entity.MemeBoard;

public class MemeResponseDto {
    private Long id;
    private String title;
    private String img;
    private int answerValue;
    private String exam1;
    private String exam2;
    private String exam3;

    public MemeResponseDto(MemeBoard memeBoard) {
        this.id = memeBoard.getId();
        this.title = memeBoard.getTitle();
        this.img = memeBoard.getImg();
        this.answerValue = memeBoard.getAnswerValue();
        this.exam1 = memeBoard.getExam1();
        this.exam2 = memeBoard.getExam2();
        this.exam3 = memeBoard.getExam3();
    }
}
