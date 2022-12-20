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
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String comment;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private MemeBoard memeBoard;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
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
