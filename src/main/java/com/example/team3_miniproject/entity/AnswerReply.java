package com.example.team3_miniproject.entity;

import com.example.team3_miniproject.dto.AnswerReplyResponseDto;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MemeBoard memeBoard;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public AnswerReply(AnswerReplyResponseDto answerReplyResponseDto, MemeBoard memeBoard, User user) {
        this.id = answerReplyResponseDto.getId();
        this.comment = answerReplyResponseDto.getComment();
        this.username = answerReplyResponseDto.getUsername();
        this.memeBoard = memeBoard;
        this.user = user;
    }

    public void update(AnswerReplyResponseDto answerReplyResponseDto) {
        this.comment  = answerReplyResponseDto.getComment();
    }
}
