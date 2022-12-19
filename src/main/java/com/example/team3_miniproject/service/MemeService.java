package com.example.team3_miniproject.service;

import com.example.team3_miniproject.dto.MemeRequestDto;
import com.example.team3_miniproject.dto.MemeResponseDto;
import com.example.team3_miniproject.entity.Answer;
import com.example.team3_miniproject.entity.Attachment;
import com.example.team3_miniproject.entity.MemeBoard;
import com.example.team3_miniproject.entity.User;
import com.example.team3_miniproject.repository.AnswerRepository;
import com.example.team3_miniproject.repository.AttachmentRepository;
import com.example.team3_miniproject.repository.MemeRepository;
import com.example.team3_miniproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemeService {

    private final MemeRepository memeRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final AttachmentRepository attachmentRepository;

    public MemeResponseDto saveMeme(MemeRequestDto requestDto, User user) {
        checkUserExists(userRepository, user);
        MemeBoard meme = memeRepository.save(requestDto.toEntity());
        return new MemeResponseDto(meme);
    }

    public Page<MemeResponseDto> getMemeList(Pageable pageable) {
        Page<MemeBoard> memes = memeRepository.findAll(pageable);
        return memes.map(e -> new MemeResponseDto(e));
    }

    // 선택 페이지 조회
    // 작성자 : 김규리
    public MemeResponseDto getMemos(Long id) {
        MemeBoard memeBoard = memeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("확인할 게시글이 없습니다.")
        );
        Attachment attachment = attachmentRepository.findByMemeBoardId(id);
        return new MemeResponseDto(memeBoard, attachment);
    }

    // 밈 게시글 수정
    @Transactional
    public MemeResponseDto updateMeme(Long id, MemeRequestDto memeRequestDto) {
        // id와 일치하는 게시글 유무
        MemeBoard memeBoard = memeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("수정할 밈 게시글이 없습니다.")
        );

        // 게시글 작성자 확인
        if (memeBoard.getUsername().equals(memeRequestDto.getUsername())) {
            // 게시글 업데이트
            memeBoard.update(memeRequestDto);
            // 게시글 정답정보 삭제
            answerRepository.deleteByMemeBoard(memeBoard);
            return new MemeResponseDto(memeBoard);
        } else {
            throw new RuntimeException("게시글 작성자가 아닙니다");
        }
    }

    @Transactional
    public void deleteMeme(Long id, User user) {
        checkUserExists(userRepository, user);
        MemeBoard meme = checkMemeBoardExists(memeRepository, id);
        memeRepository.delete(meme);
        answerRepository.deleteByMemeBoard(meme);
    }

    private User checkUserExists(UserRepository userRepository, User user) {
        return userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new RuntimeException("계정 정보가 없습니다.")
        );
    }

    private MemeBoard checkMemeBoardExists(MemeRepository memeRepository, Long id) {
        return memeRepository.findById(id).orElseThrow(
                () -> new RuntimeException("게시글이 존재하지 않습니다.")
        );
    }
}
