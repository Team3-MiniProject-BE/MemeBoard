package com.example.team3_miniproject.repository;

import com.example.team3_miniproject.entity.Answer;
import com.example.team3_miniproject.entity.AnswerReply;
import com.example.team3_miniproject.entity.MemeBoard;
import com.example.team3_miniproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Transactional
    void deleteByMemeBoard(MemeBoard memeBoard);

    Optional<Answer> findByUserIdAndMemeBoardId(Long userId, Long id);

}
