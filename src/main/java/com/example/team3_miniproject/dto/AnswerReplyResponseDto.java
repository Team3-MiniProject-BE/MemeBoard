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
    private Long MemeBoardId;
    private String username;
    private String contents;

    public AnswerReplyResponseDto(AnswerReply answerReply) {
        this.id = answerReply.getId();
        this.MemeBoardId = answerReply.getMemeBoard().getId();
        this.username = answerReply.getUsername();
        this.contents = answerReply.getComment();
    }
}
