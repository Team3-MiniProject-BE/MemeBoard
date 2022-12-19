package com.example.team3_miniproject.dto;

import com.example.team3_miniproject.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto extends Timestamped {
    private Long id;
    private String contents;

}
