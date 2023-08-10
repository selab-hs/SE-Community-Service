package com.core.service.common.response.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseMessage {

    SUCCESS(HttpStatus.OK,"SUCCESS"),
    /**
     * board 관련 response message 입니다.
     */
    CREATE_SUCCESS_BOARD(HttpStatus.CREATED,"Board를 생성 했습니다."),
    UPDATE_SUCCESS_BOARD(HttpStatus.OK,"Board를 수정 하였습니다."),
    READ_SUCCESS_BOARD(HttpStatus.OK,"해당 Board 조회에 성공했습니다."),
    READ_SUCCESS_ALL_BOARD(HttpStatus.OK,"전체 Board 조회를 성공 했습니다."),

    READ_SUCCESS_BOARD_VIEW_COUNT(HttpStatus.OK,"해당 Board 조회수 조회를 성공 했습니다."),
    DELETE_SUCCESS_BOARD(HttpStatus.OK,"해당 Board 삭제 성공 했습니다."),
    
    /**
     * comment 관련 response message 입니다.
     */
    CREATE_SUCCESS_COMMENT(HttpStatus.CREATED,"댓글 생성에 성공했습니다."),
    UPDATE_SUCCESS_COMMENT(HttpStatus.CREATED,"댓글 수정에 성공했습니다."),
    READ_ALL_SUCCESS_COMMENT(HttpStatus.CREATED,"해당 보드 전체 댓글 읽기에 성공했습니다."),
    DELETE_SUCCESS_COMMENT(HttpStatus.CREATED,"댓글 삭제에 성공했습니다.")
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
