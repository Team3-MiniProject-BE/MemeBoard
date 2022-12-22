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
    private Long id;                                    // 댓글 Id
    private Long memeBoardId;                           // 게시물 Id
    private String username;                            // 작성자 Username
    private String comment;                             // 댓글 내용
    private boolean iscorrect;                          // 댓글 작성자 = 접속 사용자 일치 여부

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
