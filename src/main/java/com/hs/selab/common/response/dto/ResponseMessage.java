package com.hs.selab.common.response.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseMessage {

    SUCCESS(HttpStatus.OK,"SUCCESS"),

    // 회원 성공 message

    CREATE_SUCCESS_MEMBER(HttpStatus.CREATED, "멤버 회원 가입 성공"),
    SUCCESS_LOAD_MEMBER_INFORMATION(HttpStatus.OK, "회원 정보 조회 성공"),
    SUCCESS_SEARCH_ALL_MEMBER(HttpStatus.OK, "모든 회원 조회 성공"),
    SUCCESS_UPDATE_MEMBER(HttpStatus.OK, "회원 정보 변경 성공"),

    // 게시판 성공 message
    CREATE_SUCCESS_BOARD(HttpStatus.CREATED,"Board를 생성 했습니다."),
    UPDATE_SUCCESS_BOARD(HttpStatus.OK,"Board를 수정 하였습니다."),
    READ_SUCCESS_BOARD(HttpStatus.OK,"해당 Board 조회에 성공했습니다."),
    READ_SUCCESS_ALL_BOARD(HttpStatus.OK,"전체 Board 조회를 성공 했습니다."),

    READ_SUCCESS_BOARD_VIEW_COUNT(HttpStatus.OK,"해당 Board 조회수 조회를 성공 했습니다."),
    DELETE_SUCCESS_BOARD(HttpStatus.OK,"해당 Board 삭제 성공 했습니다."),
    UPLOAD_IMAGE_SUCCESS(HttpStatus.CREATED, "이미지 업로드 성공"),

    // 댓글 성공 message
    CREATE_SUCCESS_COMMENT(HttpStatus.CREATED,"댓글 생성 성공 했습니다."),
    UPDATE_SUCCESS_COMMENT(HttpStatus.OK,"댓글 수정 성공 했습니다."),
    READ_ALL_SUCCESS_COMMENT(HttpStatus.OK,"전체 댓글 읽기 성공 했습니다."),
    DELETE_SUCCESS_COMMENT(HttpStatus.OK,"해당 댓글 읽기 성공 했습니다."),
    ;

    public final static String SUCCESS_MESSAGE = "SUCCESS";
    private final static String NOT_FOUND_MESSAGE = "NOT FOUND";


    private final HttpStatus status;
    private final String message;

    ResponseMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
