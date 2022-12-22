package com.example.team3_miniproject.dto;

import com.example.team3_miniproject.entity.AnswerReply;
import com.example.team3_miniproject.entity.Attachment;
import com.example.team3_miniproject.entity.MemeBoard;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MemeResponseDto {
    private Long id;                // 게시물 Id
    private String title;           // 게시물 제목
    private String contents;        // 게시물 내용
    private String username;        // 작성자 Id
    private String nickname;        // 작성자 닉네임
    private LocalDateTime createdAt;    // 생성일자
    private String img;                 // 이미지 파일 Url
    private int answerValue;            // 정답 번호
    private String exam1;               // 정답 예시 1
    private String exam2;               // 정답 예시 2
    private String exam3;               // 정답 예시 3
    private boolean isCorrect;          // 일치 여부
    private boolean boardUser;          // 로그인 사용자 작성자 일치 여부
    private List<AnswerReply> answerReplyList; // 댓글 리스트


    public MemeResponseDto(MemeBoard meme) {
        this.id = meme.getId();
        this.isCorrect = meme.isIsCorrect();
        this.title = meme.getTitle();
        this.contents = meme.getContents();
        this.username = meme.getUsername();
        this.nickname = meme.getNickname();
        this.createdAt = meme.getCreatedAt();
        this.img = meme.getAttachedFile();
        this.answerValue = meme.getAnswerValue();
        this.exam1 = meme.getExam1();
        this.exam2 = meme.getExam2();
        this.exam3 = meme.getExam3();
        this.answerReplyList = meme.getAnswerReplyList();
    }

    public MemeResponseDto(MemeBoard meme, boolean isCorrect, boolean boardUser) {
        this.id = meme.getId();
        this.isCorrect = isCorrect;
        this.title = meme.getTitle();
        this.contents = meme.getContents();
        this.username = meme.getUsername();
        this.nickname = meme.getNickname();
        this.createdAt = meme.getCreatedAt();
        this.img = meme.getAttachedFile();
        this.answerValue = meme.getAnswerValue();
        this.exam1 = meme.getExam1();
        this.exam2 = meme.getExam2();
        this.exam3 = meme.getExam3();
        this.answerReplyList = meme.getAnswerReplyList();
        this.boardUser = boardUser;
    }

}
