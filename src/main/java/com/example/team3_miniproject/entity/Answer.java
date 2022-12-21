package com.example.team3_miniproject.entity;

import com.example.team3_miniproject.dto.MemeRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Answer {
    @Id
    @Column(name = "ANSWER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int answerValue;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "MEMEBOARD_ID", nullable = false)
    private MemeBoard memeBoard;

    @Builder
    public Answer(int answerValue, User user, MemeBoard memeBoard){
        this.answerValue = answerValue;
        this.user = user;
        this.memeBoard = memeBoard;
    }
}
