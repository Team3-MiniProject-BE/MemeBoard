package com.example.team3_miniproject.service;

import com.example.team3_miniproject.dto.AnswerRequestDto;
import com.example.team3_miniproject.dto.MemeListResponseDto;
import com.example.team3_miniproject.dto.MemeRequestDto;
import com.example.team3_miniproject.dto.MemeResponseDto;
import com.example.team3_miniproject.entity.Answer;
import com.example.team3_miniproject.entity.MemeBoard;
import com.example.team3_miniproject.entity.User;
import com.example.team3_miniproject.repository.AnswerReplyRepository;
import com.example.team3_miniproject.exception.ErrorCode;
import com.example.team3_miniproject.exception.RequestException;
import com.example.team3_miniproject.repository.AnswerRepository;
import com.example.team3_miniproject.repository.MemeRepository;
import com.example.team3_miniproject.repository.UserRepository;
import com.example.team3_miniproject.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MemeService {

    private final MemeRepository memeRepository;
    private final AnswerRepository answerRepository;
    private final AnswerReplyRepository answerReplyRepository;
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;


    // 밈 게시물 작성
    // 작성자 : 박소연, 수정자 : 김규리(12/20)
    public MemeResponseDto saveMeme(MemeRequestDto requestDto, User user, MultipartFile multipartFile, String dirName ) throws IOException {
        System.out.println("밈 게시물 작성 서비스 로직 시작");
        MemeBoard meme;

        checkUserExists(userRepository, user);
        String attachedFiles = s3Uploader.upload(multipartFile, dirName);                 // 사진 업로드 (s3 Uploader)
        meme = memeRepository.save(requestDto.toEntity(attachedFiles, user));             // 업로드 파일 경로 + RequestDto(+User) DB에 저장
        System.out.println("밈 게시물 작성 서비스 로직 끝");
        return new MemeResponseDto(meme);
    }

    public List<MemeListResponseDto> getMemeList() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        List<MemeBoard> memes = memeRepository.findAll(sort);
        return memes.stream().map(MemeListResponseDto::new).collect(Collectors.toList());
    }

    // 선택 페이지 조회
    // 작성자 : 김규리
    public MemeResponseDto getMemos(Long id, User user) {
        MemeBoard memeBoard = memeRepository.findById(id).orElseThrow(
                () -> new RequestException(ErrorCode.NOT_FOUND_BOARD_404)                  // 확인할 게시글이 없습니다.
        );
        Optional<Answer> answer = answerRepository.findByUserIdAndMemeBoardId(user.getId(), id);

        if (answer.isEmpty()){
            return new MemeResponseDto(memeBoard,false);
        }else {
            return new MemeResponseDto(memeBoard, true);
        }
    }

    // 밈 게시글 수정
    @Transactional
    public MemeResponseDto updateMeme(Long id, MemeRequestDto memeRequestDto, MultipartFile multipartFile, String dirName, User user) throws IOException {
        // id와 일치하는 게시글 유무
        MemeBoard memeBoard = memeRepository.findById(id).orElseThrow(
                () -> new RequestException(ErrorCode.NOT_FOUND_BOARD_404)                   // 수정할 밈 게시글이 없습니다.
        );

        String attachedFiles = s3Uploader.upload(multipartFile, dirName);

        // 게시글 작성자 확인
        if (memeBoard.getUsername().equals(user.getUsername())) {
            // 게시글 업데이트
            memeBoard.update(memeRequestDto,attachedFiles);
            // 게시글 정답정보 삭제
            answerRepository.deleteByMemeBoard(memeBoard);
            return new MemeResponseDto(memeBoard);
        } else {
            throw new RequestException(ErrorCode.NULL_USER_ACCESS_403);                      // 게시글 작성자가 아닙니다.
        }
    }

    @Transactional
    public MemeResponseDto incollectAnswer(Long id, AnswerRequestDto request, User user){

        MemeBoard memeBoard = memeRepository.findById(id).orElseThrow(
                () -> new RequestException(ErrorCode.NOT_FOUND_BOARD_404)                   // 게시물이 존재하지 않습니다.
        );

        if (memeBoard.getAnswerValue() == request.getAnswerValue()){
            answerRepository.save(request.toEntity(user, memeBoard));
            return new MemeResponseDto(memeBoard);
        } else {
            throw new RequestException(ErrorCode.FALSE_ANSWER_405);                         // 정답이 아닙니다.
        }
    }

    @Transactional
    public void deleteMeme(Long id, User user) {
        checkUserExists(userRepository, user);
        MemeBoard meme = checkMemeBoardExists(memeRepository, id);

        if (meme.getUsername().equals(user.getUsername())) {
            memeRepository.delete(meme);
            answerRepository.deleteByMemeBoard(meme);
        } else {
            throw new RequestException(ErrorCode.NULL_USER_ACCESS_403);                      // 게시글 작성자가 아닙니다.
        }
    }

    private User checkUserExists(UserRepository userRepository, User user) {
        return userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> new RequestException(ErrorCode.NULL_ACCOUNT_400)                  // 계정 정보가 없습니다
        );
    }

    private MemeBoard checkMemeBoardExists(MemeRepository memeRepository, Long id) {
        return memeRepository.findById(id).orElseThrow(
                () -> new RequestException(ErrorCode.NOT_FOUND_BOARD_404)               // 게시글이 없습니다
        );
    }
}
