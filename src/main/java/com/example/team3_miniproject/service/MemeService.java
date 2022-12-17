package com.example.team3_miniproject.service;

import com.example.team3_miniproject.dto.MemeRequestDto;
import com.example.team3_miniproject.dto.MemeResponseDto;
import com.example.team3_miniproject.entity.MemeBoard;
import com.example.team3_miniproject.repository.MemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemeService {

    private final MemeRepository memeRepository;

    public MemeResponseDto saveMeme(MemeRequestDto requestDto) {
        MemeBoard meme = memeRepository.save(requestDto.toEntity());
        return new MemeResponseDto(meme);
    }

    public Page<MemeResponseDto> getMemeList(Pageable pageable) {
        Page<MemeBoard> memes = memeRepository.findAll(pageable);
        return memes.map(e -> new MemeResponseDto(e));
    }
}
