package com.example.team3_miniproject.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemeBoard extends Timestamped{
    @Id
    @Column(name = "MEMEBOARD_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String title;

//    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private int answerValue;

    @Column(nullable = false)
    private String exam1;

    @Column(nullable = false)
    private String exam2;

    @Column(nullable = false)
    private String exam3;

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
}
