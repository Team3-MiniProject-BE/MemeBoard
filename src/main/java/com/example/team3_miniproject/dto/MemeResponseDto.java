package com.example.team3_miniproject.dto;

import com.example.team3_miniproject.entity.AnswerReply;
import com.example.team3_miniproject.entity.MemeBoard;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MemeResponseDto {
    private Long id;
    private String title;
    private String nickname;
    private LocalDateTime createdAt;
    private String img;
    private int answerValue;
    private String exam1;
    private String exam2;
    private String exam3;
    private boolean IsCorrect;
    private List<AnswerReply> answerReplyList = new ArrayList<>();

    public MemeResponseDto(MemeBoard meme) {
        this.id = meme.getId();
        this.IsCorrect = meme.isIsCorrect();
        this.title = meme.getTitle();
        this.nickname = meme.getNickname();
        this.createdAt = meme.getCreatedAt();
        this.img = meme.getAttachedFile();
        this.answerValue = meme.getAnswerValue();
        this.exam1 = meme.getExam1();
        this.exam2 = meme.getExam2();
        this.exam3 = meme.getExam3();
        this.answerReplyList = meme.getAnswerReplyList();
    }
}
