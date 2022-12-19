package com.example.team3_miniproject.repository;

import com.example.team3_miniproject.entity.Answer;
import com.example.team3_miniproject.entity.MemeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Transactional
    void deleteByMemeBoard(MemeBoard memeBoard);
}
