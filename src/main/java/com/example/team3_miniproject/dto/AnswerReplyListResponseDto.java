package com.example.team3_miniproject.dto;

import com.example.team3_miniproject.entity.AnswerReply;
import com.example.team3_miniproject.entity.MemeBoard;
import com.example.team3_miniproject.entity.User;
import lombok.Getter;

@Getter
public class AnswerReplyListResponseDto {
    private Long id;
    private Long memeBoardId;
    private Long userId;
    private String comment;
    private boolean iscorrect;

    public AnswerReplyListResponseDto(AnswerReply answerReply){
        this.id = answerReply.getId();
        this.memeBoardId = answerReply.getMemeBoard().getId();
        this.userId = answerReply.getUser().getId();
        this.comment = answerReply.getComment();
        this.iscorrect = iscorrect;
    }

    public AnswerReplyListResponseDto(AnswerReply answerReply, boolean iscorrect){
        this.id = answerReply.getId();
        this.memeBoardId = answerReply.getMemeBoard().getId();
        this.userId = answerReply.getUser().getId();
        this.comment = answerReply.getComment();
        this.iscorrect = iscorrect;
    }
}
