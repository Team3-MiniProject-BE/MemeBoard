package com.example.team3_miniproject.service;

import com.example.team3_miniproject.dto.AnswerReplyResponseDto;
import com.example.team3_miniproject.dto.MessageResponseDto;
import com.example.team3_miniproject.entity.AnswerReply;
import com.example.team3_miniproject.entity.MemeBoard;
import com.example.team3_miniproject.entity.User;
import com.example.team3_miniproject.repository.AnswerReplyRepository;
import com.example.team3_miniproject.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
import org.h2.api.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        MemeBoard memeBoard = memeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("댓글을 추가할 게시글이 없습니다.")
        );

        AnswerReply answerReply = answerReplyRepository.save(new AnswerReply(answerReplyResponseDto, memeBoard, user));
        return new AnswerReplyResponseDto(answerReply);
    }

    // 댓글 수정
    @Transactional
    public AnswerReplyResponseDto updateAnswerReply(Long id, AnswerReplyResponseDto answerReplyResponseDto, User user) {
        AnswerReply answerReply = answerReplyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("수정할 댓글이 없습니다.")
        );

        if (answerReply.getUsername().equals(user.getUsername())) {
            answerReply.update(answerReplyResponseDto);
            return new AnswerReplyResponseDto((answerReply));
        }else {
            throw new RuntimeException("유저 권한이 없습니다.");
        }
    }

    // 댓글 삭제
    public AnswerReplyResponseDto deleteAnswerReply(Long id, User user) {
        AnswerReply answerReply = answerReplyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("삭제할 댓글이 없습니다.")
        );

        if (answerReply.getUsername().equals(user.getUsername())) {
            answerReplyRepository.delete(answerReply);
            return new AnswerReplyResponseDto((answerReply));
        }else {
            throw new RuntimeException("유저 권한이 없습니다.");
        }
    }
}
