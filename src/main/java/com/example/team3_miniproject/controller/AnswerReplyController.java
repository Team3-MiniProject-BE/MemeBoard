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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
//        return answerReplyService.addAnswerReply(answerReplyResponseDto, id, userDetails.getUser());
        return ResponseEntity.ok(new MessageResponseDto("수정 성공", HttpStatus.OK));
    }
}
