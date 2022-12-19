package com.example.team3_miniproject.repository;

import com.example.team3_miniproject.entity.MemeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemeRepository extends JpaRepository<MemeBoard, Long> {
}
