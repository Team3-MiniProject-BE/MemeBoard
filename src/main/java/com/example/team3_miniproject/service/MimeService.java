package com.example.team3_miniproject.service;

import com.example.team3_miniproject.dto.MemeRequestDto;
import com.example.team3_miniproject.dto.MemeResponseDto;
import com.example.team3_miniproject.entity.MemeBoard;
import com.example.team3_miniproject.repository.AnswerRepository;
import com.example.team3_miniproject.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MimeService {
    // 의존성 주입
    private final MemeRepository memeRepository;
    private final AnswerRepository answerRepository;

    // 밈 게시 수정
    @Transactional
    public MemeResponseDto updateMeme(long id, MemeRequestDto memeRequestDto) {
        MemeBoard memeBoard = memeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("수정할 밈 게시글이 없습니다.")
        );
        if (memeBoard.getUsername().equals(memeRequestDto.getUsername())) {
            memeBoard.update(memeRequestDto);
            answerRepository.deleteByMemeBoardAndUser(memeBoard, memeRequestDto.getUsername());
            return new MemeResponseDto(memeBoard);
        } else {
          throw new RuntimeException("게시글 작성자가 아닙니다.");
        }
    }
}
