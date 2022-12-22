package com.example.team3_miniproject.dto;

import com.example.team3_miniproject.entity.Attachment;
import com.example.team3_miniproject.entity.MemeBoard;
import com.example.team3_miniproject.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemeRequestDto {
    private Long id;                        // 게시물 Id
    private String username;                // 사용자 Id
    private String nickname;                // 닉네임
    private String password;                // 패스워드
    private String title;                   // 제목
    private String contents;                // 내용
    private int answerValue;                // 정답 번호
    private String exam1;                   // 정답 예시 1
    private String exam2;                   // 정답 예시 2
    private String exam3;                   // 정답 예시 3
    private String attachedFile;            // 첨부 파일

    public MemeBoard toEntity() {
        return MemeBoard.builder()
                .username(username)
                .nickname(nickname)
                .password(password)
                .title(title)
                .answerValue(answerValue)
                .exam1(exam1)
                .exam2(exam2)
                .exam3(exam3)
                .attachedFile(attachedFile)
                .build();
    }

    public MemeBoard toEntity(String attachedFiles, User user) {
        return MemeBoard.builder()
                .username(user.getUsername())
                .nickname(user.getNickname())
                .password(user.getPassword())
                .title(title)
                .contents(contents)
                .answerValue(answerValue)
                .exam1(exam1)
                .exam2(exam2)
                .exam3(exam3)
                .attachedFile(attachedFiles)
                .build();
    }
}
