package com.core.service.member.domain.vo;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.member.InvalidEmailException;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class Email {
    private static final String EMAIL_REGEX = "[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
    private String email;

    public Email(String email) {
        validateEmail(email);
    }

    protected Email() {
    }

    private void validateEmail(String email) {
        if(!Pattern.matches(EMAIL_REGEX, email)){
            throw new InvalidEmailException(ErrorMessage.INVALID_EMAIL_REGEX_EXCEPTION, "유효한 이메일 형식이 아닙니다");
        };
        this.email = email;
    }
}
