package com.example.team3_miniproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class MemeBoard {
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

    @Column(nullable = false)
    private String img;

    @Column(nullable = false)
    private int answerValue;

    @Column(nullable = false)
    private String exam1;

    @Column(nullable = false)
    private String exam2;

    @Column(nullable = false)
    private String exam3;
}

