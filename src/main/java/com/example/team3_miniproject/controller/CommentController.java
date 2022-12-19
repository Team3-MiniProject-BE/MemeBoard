package com.example.team3_miniproject.controller;

import com.example.team3_miniproject.dto.CommentRequestDto;
import com.example.team3_miniproject.dto.MessageResponseDto;
import com.example.team3_miniproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private CommentService commentService;

    @PostMapping("/api/memecomment/{memepostid}")
    public ResponseEntity<MessageResponseDto> saveComment(@PathVariable Long id,
                                                          @RequestBody CommentRequestDto commentRequestDto) {
        commentService.saveComment(commentRequestDto, id);
        return ResponseEntity.ok(new MessageResponseDto("댓글 등록 완료", HttpStatus.OK));
    }
}
