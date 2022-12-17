package com.example.team3_miniproject.controller;

import com.example.team3_miniproject.dto.MemeRequestDto;
import com.example.team3_miniproject.dto.MessageResponseDto;
import com.example.team3_miniproject.service.MimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemeController {
    private final MimeService mineService;

    // 밈 페이지 수정
    @PatchMapping("api/meme/{memepostId}")
    public ResponseEntity<MessageResponseDto> updateMeme(@PathVariable long id,
                                     @RequestBody MemeRequestDto memeRequestDto) {
        mineService.updateMeme(id, memeRequestDto);
        return ResponseEntity.ok(new MessageResponseDto(HttpStatus.OK, "수정 성공"));
    }
}
