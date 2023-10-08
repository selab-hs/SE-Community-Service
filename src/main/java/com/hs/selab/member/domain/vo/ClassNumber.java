package com.hs.selab.member.domain.vo;

import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.member.InvalidEmailException;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class ClassNumber {
    private static final String CLASS_CLASS_NUMBER_REGEX = "^[0-9]{2}학번$";
    private String classNumber;

    public ClassNumber(String classNumber) {
        validateEmail(classNumber);
    }

    protected ClassNumber() {
    }

    private void validateEmail(String classNumber) {
        if(!Pattern.matches(CLASS_CLASS_NUMBER_REGEX, classNumber)){
            throw new InvalidEmailException(ErrorMessage.INVALID_CLASS_NUMBER_REGEX_EXCEPTION, "유효한 학번이 아닙니다");
        };
        this.classNumber = classNumber;
    }
}
