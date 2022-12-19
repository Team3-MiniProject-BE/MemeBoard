package com.example.team3_miniproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MemeBoard memeBoard;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @Column(nullable = false)
    private String commentContents;

    @Column(nullable = false)
    private String commentUsername;

    @Column(nullable = false)
    private String commentNickname;
}
