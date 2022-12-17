package com.example.team3_miniproject.service;

import com.example.team3_miniproject.dto.MemeRequestDto;
import com.example.team3_miniproject.dto.MemeResponseDto;
import com.example.team3_miniproject.entity.Answer;
import com.example.team3_miniproject.entity.AnswerReply;
import com.example.team3_miniproject.entity.MemeBoard;
import com.example.team3_miniproject.repository.AnswerRepository;
import com.example.team3_miniproject.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemeService {

    private final MemeRepository memeRepository;
    private final AnswerRepository answerRepository;

    public MemeResponseDto saveMeme(MemeRequestDto requestDto) {
        MemeBoard meme = memeRepository.save(requestDto.toEntity());
        return new MemeResponseDto(meme);
    }

    public Page<MemeResponseDto> getMemeList(Pageable pageable) {
        Page<MemeBoard> memes = memeRepository.findAll(pageable);
        return memes.map(e -> new MemeResponseDto(e));
    }

    // 밈 게시글 수정
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
                () -> new RuntimeException("삭제할 정보가 없습니다.")
        );
        answerRepository.delete(answer);
    }

    public void deleteMeme(Long id) {
        MemeBoard meme = memeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("해당 게시글이 없습니다.")
        );
        memeRepository.delete(meme);
    }
}
