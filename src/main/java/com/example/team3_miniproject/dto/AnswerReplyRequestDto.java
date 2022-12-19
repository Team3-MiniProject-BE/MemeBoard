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
    private Long id;
    private Long MemeBoardId;
    private String username;
    private String contents;
}
