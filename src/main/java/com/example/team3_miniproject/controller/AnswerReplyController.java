package com.example.team3_miniproject.controller;

import com.example.team3_miniproject.dto.AnswerReplyRequestDto;
import com.example.team3_miniproject.dto.AnswerReplyResponseDto;
import com.example.team3_miniproject.dto.MessageResponseDto;
import com.example.team3_miniproject.security.UserDetailsImpl;
import com.example.team3_miniproject.service.AnswerReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AnswerReplyController {
    private final AnswerReplyService answerReplyService;

    // 댓글 추가
    @PostMapping("/api/meme/{id}")
    public ResponseEntity<MessageResponseDto> addComment(@RequestBody AnswerReplyResponseDto answerReplyResponseDto,
                                                             @PathVariable Long id,
                                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        answerReplyService.addAnswerReply(answerReplyResponseDto, id ,userDetails.getUser());
        return ResponseEntity.ok(new MessageResponseDto("댓글 추가 성공", HttpStatus.OK));
    }

    // 댓글 수정
    @PatchMapping("/api/memecomment/{id}")
    public ResponseEntity<MessageResponseDto> updateComment(@PathVariable Long id,
                                    @RequestBody AnswerReplyResponseDto answerReplyResponseDto,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        answerReplyService.updateAnswerReply(id, answerReplyResponseDto, userDetails.getUser());
        return ResponseEntity.ok(new MessageResponseDto("댓글 수정 성공", HttpStatus.OK));
    }

    // 댓글 삭제
    @DeleteMapping("/api/memecomment/{id}")
    public ResponseEntity<MessageResponseDto> deleteComment(@PathVariable Long id,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        answerReplyService.deleteAnswerReply(id, userDetails.getUser());
        return ResponseEntity.ok(new MessageResponseDto("댓글 삭제 성공", HttpStatus.OK));
    }
}
