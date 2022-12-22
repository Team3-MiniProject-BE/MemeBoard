package com.example.team3_miniproject.config;

import com.example.team3_miniproject.jwt.JwtUtil;
import com.example.team3_miniproject.jwt.JwtAuthFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig  implements WebMvcConfigurer {
    private final JwtUtil jwtUtil;
    private final ObjectMapper om;
    private final long MAX_AGE_SECS = 3600;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        //h2-console 사용 및 resources 접근 허용 설정
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    // CORS Config
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:80", "http://localhost:8080", "http://localhost:3000", "http://54.180.86.147/")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE", "HEAD", "OPTIONS")
                .allowCredentials(true)
                .exposedHeaders("X-AUTH-TOKEN")
                .maxAge(MAX_AGE_SECS);
    }

    // CORS Config
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){

        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("http://the-greatest-minkyu.s3-website.ap-northeast-2.amazonaws.com/");         // 허용할 URl (리액트 배포 URL)

        config.addAllowedOrigin("http://localhost:3000");               // 허용할 URl (리액트 테스트 URL)

        config.addAllowedOrigin("http://localhost:80");                 // 허용할 URl (백엔드 테스트 URL)

        config.addAllowedOrigin("http://localhost:8080");               // 허용할 URl (백엔드 테스트 URL)

        config.addAllowedOrigin("http://54.180.86.147/");               // 허용할 URl (AWS EC2 IP)

        config.addExposedHeader(JwtUtil.AUTHORIZATION_HEADER);          // Header에 인증키(JWT 토큰) 추가

        config.addAllowedMethod("*");                                   // 메서드 전체 허용

        config.addAllowedHeader("*");                                   // 헤더 전체 허용

        config.setAllowCredentials(true);                               // 내 서버가 응답할 때 json을 JS에서 처리할 수 있게 설정

        config.validateAllowCredentials();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();                                                                                          //일반적인 루트가 아닌 다른 방식으로 요청시 거절, header에 id, pw가 아닌 token(jwt)을 달고 간다. 그래서 basic이 아닌 bearer를 사용한다.
        http.httpBasic().disable()
                .authorizeRequests()                                                                                    //요청에 대한 사용권한에 대해서 체크함
                .antMatchers("/api/signup-idcheck/**").permitAll()
                .antMatchers("/api/signup-nicknamecheck/**").permitAll()
                .antMatchers("/api/signup**").permitAll()                                                    // 회원가입/ 로그인 Url은 권한을 전체 허용
                .antMatchers("/api/login**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/memes").permitAll()
                .anyRequest().authenticated()// 그 외 요청은 authentication이라는 객체가 Security Context에 있는지 확인함
                .and()
                .cors()                                                                                                  // 위에서 설정한 CORS Setting
                .and()
                .sessionManagement()
                .and()
                .apply(new JwtConfig(jwtUtil, om));
//                .and()
//                .addFilterBefore(new JwtAuthFilter(jwtUtil),
//                        UsernamePasswordAuthenticationFilter.class);                                                    // JwtAuthenticationFilter를 UsernamePasswordAuthenticationFilter 전에 넣고, 토큰에 저장된 유저정보를 활용하여야 하기 때문에 CustomUserDetailService 클래스를 생성한다.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
