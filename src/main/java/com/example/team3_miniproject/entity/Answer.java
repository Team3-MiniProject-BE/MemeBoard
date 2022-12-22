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
    private Long id;                                            // 정답 Id

    @Column(nullable = false)
    private int answerValue;                                    // 정답 값

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;                                          // 사용자 정보

    @OneToOne
    @JoinColumn(name = "MEMEBOARD_ID", nullable = false)
    private MemeBoard memeBoard;                                // 게시물 정보

    @Builder
    public Answer(int answerValue, User user, MemeBoard memeBoard){
        this.answerValue = answerValue;
        this.user = user;
        this.memeBoard = memeBoard;
    }
}
