package com.example.team3_miniproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /*
     * 4xx 클라이언트 오류
     * 400 Bad Request : 잘못된 요청.
     * 401 Unauthorized : 인증이 필요함.
     * 403 Forbidden : 접근 권한 없음.
     * 404 Not Found : Resource 없음.
     * 405 Methods Not Allowed : 유효하지 않은 요청
     * 409 Conflict : 리소스 충돌(중복)
     *
     * 5xx 서버 오류
     * 500 Internal Server Error : 서버 오류 발생
     * 503 Service Unavailable : 서비스 사용 불가
     */
    NOT_FOUND_BOARD_404         (HttpStatus.NOT_FOUND, "게시글이 없습니다."),
    NOT_FOUND_COMMENT_404       (HttpStatus.NOT_FOUND, "댓글이 없습니다."),
    FALSE_ANSWER_405            (HttpStatus.METHOD_NOT_ALLOWED, "정답이 아닙니다."),
    NULL_ACCOUNT_400            (HttpStatus.BAD_REQUEST, "계정 정보가 없습니다."),
    NULL_USER_400               (HttpStatus.BAD_REQUEST, "유저 정보가 없습니다."),
    NON_MATCHING_PASSWORD_400   (HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니"),
    CONFLICT_ACCOUNT_409        (HttpStatus.CONFLICT, "중복된 아이디가 있습니다"),
    NULL_USER_ACCESS_403        (HttpStatus.FORBIDDEN, "작성자가 아닙니다.");

    private final HttpStatus httpStatus;
    private final String message;
}