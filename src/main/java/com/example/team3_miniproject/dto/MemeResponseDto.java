package com.example.team3_miniproject.dto;

import com.example.team3_miniproject.entity.MemeBoard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemeResponseDto {
    private Long id;
    private String title;
    private String nickname;
    private LocalDateTime createdAt;

    public MemeResponseDto(MemeBoard meme) {
        this.id = meme.getId();
        this.title = meme.getTitle();
        this.nickname = meme.getNickname();
        this.createdAt = meme.getCreatedAt();
    }
}
