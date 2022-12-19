package com.example.team3_miniproject.service;

import com.example.team3_miniproject.dto.AnswerReplyResponseDto;
import com.example.team3_miniproject.entity.AnswerReply;
import com.example.team3_miniproject.entity.MemeBoard;
import com.example.team3_miniproject.entity.User;
import com.example.team3_miniproject.repository.AnswerReplyRepository;
import com.example.team3_miniproject.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerReplyService {
    // 의존성 주입
    private final MemeRepository memeRepository;
    private final AnswerReplyRepository answerReplyRepository;

    // 댓글 추가
    public AnswerReplyResponseDto addAnswerReply(AnswerReplyResponseDto answerReplyResponseDto,
                                                 Long id,
                                                 User user) {
        MemeBoard memeBoard = memeRepository.findById(id).orElseThrow(             // 댓글을 추가할 게시글을 찾는다.
                () -> new RuntimeException("댓글을 추가할 게시글이 없습니다.")
        );

        AnswerReply answerReply = AnswerReply.builder()
                .id(answerReplyResponseDto.getId())
                .memeBoard(memeBoard)
                .user(user)
                .username(user.getUsername())
                .comment(answerReplyResponseDto.getContents())
                .build();

        answerReplyRepository.save(answerReply);
        return new AnswerReplyResponseDto(answerReply);
    }
}
