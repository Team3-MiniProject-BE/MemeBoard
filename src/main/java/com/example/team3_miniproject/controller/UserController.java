package com.example.team3_miniproject.controller;

import com.example.team3_miniproject.dto.LoginRequestDto;
import com.example.team3_miniproject.dto.MessageResponseDto;
import com.example.team3_miniproject.dto.SignUpRequestDto;
import com.example.team3_miniproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원가입
    @ResponseBody
    @PostMapping("/api/signup")
    public ResponseEntity<MessageResponseDto> signup(@RequestBody SignUpRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return ResponseEntity.ok(new MessageResponseDto("가입완료", HttpStatus.OK));
    }

    // 로그인
    @ResponseBody
    @PostMapping("/api/login")
    public ResponseEntity<MessageResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return ResponseEntity.ok(new MessageResponseDto("로그인 성공", HttpStatus.OK));
    }

    // 아이디 중복 확인
    @GetMapping("/api/signup-idcheck/{username}")
    public ResponseEntity<Boolean> checkUserNameDuplicate (@PathVariable String username){
        return ResponseEntity.ok(userService.checkUserNameDuplicate(username));
    }

    // 닉네임 중복 확인
    @GetMapping("/api/signup-nicknamecheck/{nickname}")
    public ResponseEntity<Boolean> checkNickNameDuplicate (@PathVariable String nickname){
        return ResponseEntity.ok(userService.checkNickNameDuplicate(nickname));
    }
}
