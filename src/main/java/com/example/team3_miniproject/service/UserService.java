package com.example.team3_miniproject.service;

import com.example.team3_miniproject.dto.LoginRequestDto;
import com.example.team3_miniproject.dto.SignUpRequestDto;
import com.example.team3_miniproject.entity.User;
import com.example.team3_miniproject.entity.UserRoleEnum;
import com.example.team3_miniproject.jwt.JwtUtil;
import com.example.team3_miniproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    // 의존성 주입
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public void signup(SignUpRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();                           // Dto -> entity
        String password = passwordEncoder.encode(signupRequestDto.getPassword());   // Dto -> entity
        String nickname = signupRequestDto.getNickname();
        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);             // userRepository에서 유저 찾기
        if (found.isPresent()) {                                                    // 유저가 있을 경우 중복 error
            throw new RuntimeException("중복된 아이디가 있습니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new RuntimeException("관리자 토큰 비밀번호가 일치하지 않습니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role, nickname);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(            // 유저 찾기
                () -> new RuntimeException("유저가 없습니다")                           // 없을 경우 유저없음 error
        );

        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
    }
}
