package com.example.team3_miniproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Answer {
    @Id
    @Column(name = "ANSWER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "MEMEBOARD_ID", nullable = false)
    private MemeBoard memeBoard;
}
