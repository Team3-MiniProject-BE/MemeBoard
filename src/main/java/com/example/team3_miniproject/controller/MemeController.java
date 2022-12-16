package com.example.team3_miniproject.controller;

import com.example.team3_miniproject.dto.MemeRequestDto;
import com.example.team3_miniproject.dto.MessageResponseDto;
import com.example.team3_miniproject.service.MemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemeController {

    private final MemeService memeService;

    @PostMapping("/api/memepost")
    public ResponseEntity<MessageResponseDto> saveMeme(@RequestBody MemeRequestDto requestDto) {
        memeService.saveMeme(requestDto);
        return ResponseEntity.ok(new MessageResponseDto("등록 완료", HttpStatus.OK));
    }
}
