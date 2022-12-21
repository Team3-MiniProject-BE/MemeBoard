package com.example.team3_miniproject.entity;

import com.example.team3_miniproject.dto.MemeRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemeBoard extends Timestamped{
    @Id
//    @Column(name = "MEMEBOARD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private int answerValue;

    @Column(nullable = false)
    private String exam1;

    @Column(nullable = false)
    private String exam2;

    @Column(nullable = false)
    private String exam3;

    @Column(nullable = true)
    private String attachedFile;

    @Column(nullable = true)
    private boolean IsCorrect = false;

    @OneToMany(mappedBy = "memeBoard", cascade = CascadeType.ALL)
    private List<AnswerReply> answerReplyList = new ArrayList<>();

    @Builder
    public MemeBoard(String username, String nickname, String password, String title, String contents,
                     int answerValue, String exam1, String exam2, String exam3, String attachedFile) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.title = title;
        this.contents = contents;
        this.answerValue = answerValue;
        this.exam1 = exam1;
        this.exam2 = exam2;
        this.exam3 = exam3;
        this.attachedFile = attachedFile;
    }

    public void update(MemeRequestDto memeRequestDto, String attachedFile) {
        this.title = memeRequestDto.getTitle();
        this.contents = memeRequestDto.getContents();
        this.answerValue = memeRequestDto.getAnswerValue();
        this.exam1 = memeRequestDto.getExam1();
        this.exam2 = memeRequestDto.getExam2();
        this.exam3 = memeRequestDto.getExam3();
        this.attachedFile = attachedFile;
    }

    public void statusUpdate(boolean IsCorrect){
        this.IsCorrect = IsCorrect;
    }
}
