package com.example.team3_miniproject.controller;

import com.example.team3_miniproject.dto.MemeRequestDto;
import com.example.team3_miniproject.dto.MemeResponseDto;
import com.example.team3_miniproject.dto.MessageResponseDto;
import com.example.team3_miniproject.service.MemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemeController {

    private final MemeService memeService;

    @PostMapping("/api/memepost")
    public ResponseEntity<MessageResponseDto> saveMeme(@RequestBody MemeRequestDto requestDto) {
        memeService.saveMeme(requestDto);
        return ResponseEntity.ok(new MessageResponseDto("등록 완료", HttpStatus.OK));
    }

    @GetMapping("/api/memes")
    public Page<MemeResponseDto> getMemeList(@PageableDefault(size = 12) Pageable pageable) {
        return memeService.getMemeList(pageable);
    }

    // 밈 페이지 수정
    @PatchMapping("api/meme/{id}")
    public ResponseEntity<MessageResponseDto> updateMeme(@PathVariable Long id,
                                                         @RequestBody MemeRequestDto memeRequestDto) {
        memeService.updateMeme(id, memeRequestDto);
        return ResponseEntity.ok(new MessageResponseDto("수정 성공",HttpStatus.OK));
    }
}
