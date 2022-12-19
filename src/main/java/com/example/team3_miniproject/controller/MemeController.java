package com.example.team3_miniproject.controller;

import com.example.team3_miniproject.dto.MemeRequestDto;
import com.example.team3_miniproject.dto.MessageResponseDto;
import com.example.team3_miniproject.s3.S3Uploader;
import com.example.team3_miniproject.service.MemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class MemeController {

    private final MemeService memeService;
    private final S3Uploader s3Uploader;

    @PostMapping("/api/memepost")
    public ResponseEntity<MessageResponseDto> saveMeme(@RequestBody MemeRequestDto requestDto) {
        memeService.saveMeme(requestDto);
        return ResponseEntity.ok(new MessageResponseDto("등록 완료", HttpStatus.OK));
    }

    // 밈 사진 업로드 API
    @PostMapping("/api/{id}/upload")
    @ResponseBody
    public ResponseEntity<MessageResponseDto> uploadImage(@PathVariable Long id, @RequestParam("data") MultipartFile multipartFile) throws IOException {
        s3Uploader.upload(id, multipartFile, "static");
        return ResponseEntity.ok(new MessageResponseDto("이미지 업로드 완료", HttpStatus.OK));
    }
}
