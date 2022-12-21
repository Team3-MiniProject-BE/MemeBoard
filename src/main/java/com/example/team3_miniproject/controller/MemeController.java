package com.example.team3_miniproject.controller;

import com.example.team3_miniproject.dto.*;
import com.example.team3_miniproject.s3.S3Uploader;
import com.example.team3_miniproject.security.UserDetailsImpl;
import com.example.team3_miniproject.service.MemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class MemeController {

    private final MemeService memeService;
    private final S3Uploader s3Uploader;


    @PostMapping(value = "/api/memepost", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})           // 파일 첨부 + 게시판 작성을 위한 Madia Type 선언
    public ResponseEntity<MessageResponseDto> saveMeme(@RequestPart MemeRequestDto requestDto,                                            // Requestpart 어노테이션을 사용해서 requestdto로 데이터를 전달 받음
                                                       @RequestPart("data") MultipartFile multipartFile,                                  // Requestpart 어노테이션을 사용해서 data로 파일을 전달 받음
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {         // spring Security를 통한 유저 정보 전달
        System.out.println("작성 api 컨트롤러 시작");
        System.out.println("dto"+requestDto+"file"+multipartFile);
        memeService.saveMeme(requestDto, userDetails.getUser(), multipartFile, "static"); // RequestDto, User정보,  첨부파일, 업로드 디렉토리 명
        System.out.println("작성 api 컨트롤러 종료 후 리턴값 전달");
        return ResponseEntity.ok(new MessageResponseDto("등록 완료", HttpStatus.OK));
    }

    @GetMapping("/api/memes")
    public List<MemeListResponseDto> getMemeList() {
        return memeService.getMemeList();
    }

    // 선택 조회 기능
    // 작성자 : 김규리
    @GetMapping("/api/memes/{id}")
    public MemeResponseDto getMemos(@PathVariable Long id,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        MemeResponseDto memeResponseDto = memeService.getMemos(id, userDetails.getUser());
        return memeResponseDto;
    }

    // 밈 페이지 수정
    // 작성자 : 김규리
    @PatchMapping(value = "/api/meme/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})         // 파일 첨부 + 게시판 작성을 위한 Madia Type 선언
    public ResponseEntity<MessageResponseDto> updateMeme(@PathVariable Long id,                                                           // 게시물 Id 값
                                                         @RequestPart MemeRequestDto requestDto,                                          // Requestpart 어노테이션을 사용해서 requestdto로 데이터를 전달 받음
                                                         @RequestPart("data") MultipartFile multipartFile,
                                                         @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException{            // Requestpart 어노테이션을 사용해서 data로 파일을 전달 받음
        memeService.updateMeme(id, requestDto, multipartFile, "static", userDetails.getUser());                                                       // 게시물 Id, requestDto, 첨부파일, 업로드 디렉토리 명
    return ResponseEntity.ok(new MessageResponseDto("수정 성공",HttpStatus.OK));
    }
    
    // 밈 사진 업로드 API
    // 작성자 : 김규리
    @PostMapping("/api/upload")
    @ResponseBody
    public ResponseEntity<MessageResponseDto> uploadImage(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        s3Uploader.upload(multipartFile, "static");
        return ResponseEntity.ok(new MessageResponseDto("이미지 업로드 완료", HttpStatus.OK));
    }

    @DeleteMapping("/api/meme/{id}")
    public ResponseEntity<MessageResponseDto> deleteMeme(@PathVariable Long id,
                                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        memeService.deleteMeme(id, userDetails.getUser());
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK));
    }

    // 밈 퀴즈 정답 확인 API
    // 작성자 : 김규리
    @PostMapping("/api/memeanswer/{Id}")
    public ResponseEntity<MessageResponseDto> incollectAnswer(@PathVariable Long Id,
                                                              @RequestBody AnswerRequestDto request,
                                                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        MemeResponseDto memeResponseDto = memeService.incollectAnswer(Id, request, userDetails.getUser());
        return ResponseEntity.ok(new MessageResponseDto("정답입니다! 댓글을 확인해보세요",HttpStatus.OK, memeResponseDto));
    }
}
