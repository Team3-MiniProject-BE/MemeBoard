package com.example.team3_miniproject.dto;

import com.example.team3_miniproject.entity.AnswerReply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerReplyResponseDto {
    private Long id;
    private Long memeBoardId;
    private String username;
    private String comment;
    private boolean iscorrect;

    public AnswerReplyResponseDto(AnswerReply answerReply) {
        this.id = answerReply.getId();
        this.memeBoardId = answerReply.getMemeBoard().getId();
        this.username = answerReply.getUsername();
        this.comment = answerReply.getComment();
    }

    public AnswerReplyResponseDto(AnswerReply answerReply, boolean iscorrect) {
        this.id = answerReply.getId();
        this.memeBoardId = answerReply.getMemeBoard().getId();
        this.username = answerReply.getUsername();
        this.comment = answerReply.getComment();
        this.iscorrect = iscorrect;
    }
}
