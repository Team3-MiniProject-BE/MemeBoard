package com.example.team3_miniproject.repository;

import com.example.team3_miniproject.entity.AnswerReply;
import com.example.team3_miniproject.entity.MemeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerReplyRepository extends JpaRepository<AnswerReply, Long> {

    List<AnswerReply> findAllByMemeBoard(MemeBoard memeBoard);
}
