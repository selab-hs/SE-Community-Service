package com.core.service.member.domain.vo;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.member.InvalidEmailException;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class Name {
    private static final String NAME_REGEX = "^[ㄱ-ㅎ가-힣]{1,5}$";
    private String name;

    public Name(String name) {
        validateEmail(name);
    }

    private void validateEmail(String name) {
        if(!Pattern.matches(NAME_REGEX, name)){
            throw new InvalidEmailException(ErrorMessage.INVALID_NAME_REGEX_EXCEPTION, "유효한 이름이 아닙니다");
        };
        this.name = name;
    }
}
