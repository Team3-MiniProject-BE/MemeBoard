package com.example.team3_miniproject.service;

import com.example.team3_miniproject.dto.CommentRequestDto;
import com.example.team3_miniproject.entity.Comment;
import com.example.team3_miniproject.entity.MemeBoard;
import com.example.team3_miniproject.repository.AnswerReplyRepository;
import com.example.team3_miniproject.repository.CommentRepository;
import com.example.team3_miniproject.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;
    private MemeRepository memeRepository;
    private AnswerReplyRepository answerReplyRepository;

    public void saveComment(CommentRequestDto commentRequestDto, Long id) {
        MemeBoard memeBoard = memeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("수정할 밈 게시글이 없습니다.")
        );
        Comment comment = Comment.builder()
                .memeBoard(memeBoard)
                .commentUsername(memeBoard.getUsername())
                .commentNickname(memeBoard.getNickname())
                .commentContents(commentRequestDto.getContents())
                .build();
        answerReplyRepository.save(comment);
    }
}
