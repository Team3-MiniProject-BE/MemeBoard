package com.example.team3_miniproject.entity;

import com.example.team3_miniproject.dto.MemeRequestDto;
import com.example.team3_miniproject.dto.MemeResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class MemeBoard extends Timestamped {
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

    @OneToOne
    @JoinColumn(name = "meme_Id", nullable = false)
    private Answer answer;

    public void update(MemeRequestDto memeRequestDto) {
        this.title = memeRequestDto.getTitle();
        this.img = memeRequestDto.getImg();
        this.answerValue = memeRequestDto.getAnswerValue();
        this.exam1 = memeRequestDto.getExam1();
        this.exam2 = memeRequestDto.getExam2();
        this.exam3 = memeRequestDto.getExam3();
    }
}

