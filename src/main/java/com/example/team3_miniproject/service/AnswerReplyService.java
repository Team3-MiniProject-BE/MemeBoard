package com.example.team3_miniproject.service;

import com.example.team3_miniproject.dto.AnswerReplyResponseDto;
import com.example.team3_miniproject.entity.AnswerReply;
import com.example.team3_miniproject.entity.MemeBoard;
import com.example.team3_miniproject.entity.User;
import com.example.team3_miniproject.exception.ErrorCode;
import com.example.team3_miniproject.exception.RequestException;
import com.example.team3_miniproject.repository.AnswerReplyRepository;
import com.example.team3_miniproject.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
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
                () -> new RequestException(ErrorCode.NOT_FOUND_BOARD_404)
        );

        AnswerReply answerReply = answerReplyRepository.save(new AnswerReply(answerReplyResponseDto, memeBoard, user));
        return new AnswerReplyResponseDto(answerReply);
    }

    // 댓글 수정
    @Transactional
    public AnswerReplyResponseDto updateAnswerReply(Long id, AnswerReplyResponseDto answerReplyResponseDto, User user) {
        AnswerReply answerReply = answerReplyRepository.findById(id).orElseThrow(
                () -> new RequestException(ErrorCode.NOT_FOUND_COMMENT_404)
        );

        if (answerReply.getUsername().equals(user.getUsername())) {
            answerReply.update(answerReplyResponseDto);
            return new AnswerReplyResponseDto((answerReply));
        }else {
            throw new RequestException(ErrorCode.NULL_ACCOUNT_400);
        }
    }

    // 댓글 삭제
    public AnswerReplyResponseDto deleteAnswerReply(Long id, User user) {
        AnswerReply answerReply = answerReplyRepository.findById(id).orElseThrow(
                () -> new RequestException(ErrorCode.NOT_FOUND_COMMENT_404)
        );

        if (answerReply.getUsername().equals(user.getUsername())) {
            answerReplyRepository.delete(answerReply);
            return new AnswerReplyResponseDto((answerReply));
        }else {
            throw new RequestException(ErrorCode.NULL_ACCOUNT_400);
        }
    }
}
