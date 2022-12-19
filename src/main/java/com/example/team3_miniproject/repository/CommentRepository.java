package com.example.team3_miniproject.repository;

import com.example.team3_miniproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
