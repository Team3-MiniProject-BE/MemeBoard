package com.example.team3_miniproject.dto;

import com.example.team3_miniproject.entity.MemeBoard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemeListResponseDto {
    private Long id;                            // 게시물 Id
    private String title;                       // 제목
    private String nickname;                    // 닉네임
    private LocalDateTime createdAt;            // 생성일자
    private String img;                         // 이미지 Url
    private int answerValue;                    // 정답
    private String exam1;                       // 정답 예시 1
    private String exam2;                       // 정답 예시 2
    private String exam3;                       // 정답 예시 3

    public MemeListResponseDto(MemeBoard meme) {
        this.id = meme.getId();
        this.title = meme.getTitle();
        this.nickname = meme.getNickname();
        this.createdAt = meme.getCreatedAt();
        this.img = meme.getAttachedFile();
        this.answerValue = meme.getAnswerValue();
        this.exam1 = meme.getExam1();
        this.exam2 = meme.getExam2();
        this.exam3 = meme.getExam3();
    }
}
