package com.example.team3_miniproject.dto;

import com.example.team3_miniproject.entity.AnswerReply;
import com.example.team3_miniproject.entity.MemeBoard;
import com.example.team3_miniproject.entity.User;
import lombok.Getter;

@Getter
public class AnswerReplyListResponseDto {
    private Long id;                // 댓글 Id
    private Long memeBoardId;       // 게시판 Id
    private Long userId;            // 사용자 Id
    private String comment;         // 댓글
    private boolean iscorrect;      // 일치여부
    private String nickname;        // 닉네임

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
        this.nickname = answerReply.getUser().getNickname();
        this.comment = answerReply.getComment();
        this.iscorrect = iscorrect;
    }
}
