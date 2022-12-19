package com.example.team3_miniproject.repository;

import com.example.team3_miniproject.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    Attachment findByMemeBoardId(Long id);
}