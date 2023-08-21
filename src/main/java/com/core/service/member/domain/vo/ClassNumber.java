package com.core.service.member.domain.vo;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.member.InvalidEmailException;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class ClassNumber {
    private static final String CLASS_CLASS_NUMBER_REGEX = "^[0-9]{2}학번$";
    private String classNumber;

    public ClassNumber(String classNumber) {
        validateEmail(classNumber);
    }

    private void validateEmail(String classNumber) {
        if(!Pattern.matches(CLASS_CLASS_NUMBER_REGEX, classNumber)){
            throw new InvalidEmailException(ErrorMessage.INVALID_CLASS_NUMBER_REGEX_EXCEPTION, "유효한 학번이 아닙니다");
        };
        this.classNumber = classNumber;
    }
}
