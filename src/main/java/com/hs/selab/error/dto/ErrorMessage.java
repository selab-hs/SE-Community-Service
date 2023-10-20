package com.hs.selab.error.dto;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {

    /**
     * 서버 내부 오류
     */
    INTERNAL_SERVER_ERROR(HttpStatus.BAD_REQUEST, "내부 서버 오류"),
    INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 요청 입니다."),
    SIGNATURE_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 서명입니다."),
    WRONG_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, "잘못된 JWT 입니다"),
    EXPIRED_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다"),
    INVALID_EMAIL_REGEX_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 이메일 형식입니다"),
    INVALID_PASSWORD_REGEX_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 비밀번호 형식입니다"),
    INVALID_NAME_REGEX_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 이름 형식 입니다"),
    INVALID_CLASS_NUMBER_REGEX_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 학번 형식 입니다"),
    NOT_EXIST_USER_INFO_EXCEPTION(HttpStatus.NOT_FOUND, "유저 정보가 존재하지 않습니다"),
    NOT_EXIST_MEMBER_EXCEPTION(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않습니다"),
    INVALID_PASSWORD_MATCH_EXCEPTION(HttpStatus.BAD_REQUEST, "비밀번호가 맞지 않습니다"),
    ALREADY_EXIST_MEMBER_EMAIL_EXCEPTION(HttpStatus.BAD_REQUEST, "이미 해당 이메일 정보가 등록되어 있습니다."),
    INVALID_LOGIN_USER_INFORMATION_EXCEPTION(HttpStatus.NOT_FOUND, "잘못된 로그인 정보입니다"),
    NOT_LOGIN_USER_EXCEPTION(HttpStatus.BAD_REQUEST, "로그인 정보가 없습니다"),
    NON_EXISTENT_BOARD_EXCEPTION(HttpStatus.BAD_REQUEST,"해당 보드가 존재하지 않습니다."),
    UNAUTHORIZED_ACCESS_EXCEPTION(HttpStatus.BAD_REQUEST,"권한이 없는 접근 입니다."),
    INVALID_DATA_ARGUMENT(HttpStatus.BAD_REQUEST, "invalid data argument"),
    FAILURE_FILE_CONVERT(HttpStatus.BAD_REQUEST, "이미지 업로드 실패"),
    ;

    private final HttpStatus status;
    private final String message;

    ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
