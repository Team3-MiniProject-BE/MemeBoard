package com.example.team3_miniproject.service;

import com.example.team3_miniproject.dto.MemeRequestDto;
import com.example.team3_miniproject.dto.MemeResponseDto;
import com.example.team3_miniproject.entity.Answer;
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

    // 밈 게시글 수정
//    @Transactional
    public MemeResponseDto updateMeme(Long id, MemeRequestDto memeRequestDto) {
        // id와 일치하는 게시글 유무
        MemeBoard memeBoard = memeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("수정할 밈 게시글이 없습니다.")
        );

        // 게시글 작성자 확인
        if (memeBoard.getUsername().equals(memeRequestDto.getUsername())) {
            memeBoard.update(memeRequestDto);
            memeRepository.save(memeBoard);
            answerDeleteMeme(id);
            return new MemeResponseDto(memeBoard);
        } else {
            throw new RuntimeException("게시글 작성자가 아닙니다");
        }
    }

    // answer 테이블 삭제
    public void answerDeleteMeme(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("수정할 밈 게시글이 없습니다.")
        );
        answerRepository.delete(answer);
    }
}
