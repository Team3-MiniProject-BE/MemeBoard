package com.example.team3_miniproject.entity;

import com.example.team3_miniproject.dto.AnswerReplyResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AnswerReply {
    @Id
    @Column(name = "REPLY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                        // 댓글 Id

    @Column(nullable = false)
    private String username;                                // 사용자 Id

    @Column(nullable = false)
    private String nickname;                                // 닉네임

    @Column(nullable = false)
    private String comment;                                 // 댓글 내용

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private MemeBoard memeBoard;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public AnswerReply(AnswerReplyResponseDto answerReplyResponseDto, MemeBoard memeBoard, User user) {
        this.comment = answerReplyResponseDto.getComment();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.memeBoard = memeBoard;
        this.user = user;
    }

    public void update(AnswerReplyResponseDto answerReplyResponseDto) {
        this.comment  = answerReplyResponseDto.getComment();
    }
}
