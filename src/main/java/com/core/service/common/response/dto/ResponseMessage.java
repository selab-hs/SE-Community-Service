package com.core.service.common.response.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseMessage {

    SUCCESS(HttpStatus.OK,"SUCCESS"),
    CREATE_SUCCESS_MEMBER(HttpStatus.CREATED, "멤버 회원 가인 성공"),
    SUCCESS_LOAD_MEMBER_INFORMATION(HttpStatus.OK, "회원 정보 조회 성공"),
    SUCCESS_SEARCH_ALL_MEMBER(HttpStatus.OK, "모든 회원 조회 성공");

    public final static String SUCCESS_MESSAGE = "SUCCESS";
    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";


    private final HttpStatus status;
    private final String message;

    ResponseMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
