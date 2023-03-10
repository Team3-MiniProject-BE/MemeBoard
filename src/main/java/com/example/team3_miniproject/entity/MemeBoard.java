package com.example.team3_miniproject.entity;

import com.example.team3_miniproject.dto.MemeRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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
    private int answerValue;

    @Column(nullable = false)
    private String exam1;

    @Column(nullable = false)
    private String exam2;

    @Column(nullable = false)
    private String exam3;

    // JPA 연관관계 추가 (첨부파일은 게시판에 종속적이므로 게시판이 첨부파일 엔티티의 생명주기를 관리한다)
    @OneToMany(mappedBy = "memeBoard", cascade = CascadeType.ALL)
    private List<Attachment> attachedFiles = new ArrayList<>();

    @OneToMany(mappedBy = "memeBoard", cascade = CascadeType.ALL)
    private List<AnswerReply> answerReplyList = new ArrayList<>();


    @Builder
    public MemeBoard(String username, String nickname, String password, String title,
                     int answerValue, String exam1, String exam2, String exam3) {

        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.title = title;
        this.answerValue = answerValue;
        this.exam1 = exam1;
        this.exam2 = exam2;
        this.exam3 = exam3;
    }

    public void update(MemeRequestDto memeRequestDto) {
        this.title = memeRequestDto.getTitle();
        this.img = memeRequestDto.getImg();
        this.answerValue = memeRequestDto.getAnswerValue();
        this.exam1 = memeRequestDto.getExam1();
        this.exam2 = memeRequestDto.getExam2();
        this.exam3 = memeRequestDto.getExam3();
    }
}
