package com.example.team3_miniproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerReplyRequestDto {
    private Long id;                    // Reply Id
    private Long MemeBoardId;           // 밈 게시판 Id
    private String username;            // 작성자 username
    private String contents;            // 댓글 내용
}
