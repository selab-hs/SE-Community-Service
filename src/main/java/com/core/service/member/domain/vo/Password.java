package com.core.service.member.domain.vo;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.member.InvalidEmailException;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class Password {
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$";
    private String password;

    public Password(String password) {
        validatePassword(password);
    }

    protected Password() {
    }

    private void validatePassword(String password) {
        if(!Pattern.matches(PASSWORD_REGEX, password)){
            throw new InvalidEmailException(ErrorMessage.INVALID_PASSWORD_REGEX_EXCEPTION, "유효한 비밀번호 형식이 아닙니다");
        };
        this.password = password;
    }

    public void setEncodePassword(String encodePassword) {
        this.password = encodePassword;
    }
}
