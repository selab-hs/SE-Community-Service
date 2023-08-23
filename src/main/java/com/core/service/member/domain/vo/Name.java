package com.core.service.member.domain.vo;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.member.InvalidNameException;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class Name {
    private static final String NAME_REGEX = "^[ㄱ-ㅎ가-힣]{1,5}$";
    private String name;

    public Name(String name) {
        validateEmail(name);
    }

    protected Name() {
    }

    private void validateEmail(String name) {
        if(!Pattern.matches(NAME_REGEX, name)){
            throw new InvalidNameException(ErrorMessage.INVALID_NAME_REGEX_EXCEPTION, "유효한 이름이 아닙니다");
        }
        this.name = name;
    }
}
