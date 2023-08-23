package com.core.service.member.domain.vo;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.member.InvalidStudentIdException;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Getter
@Embeddable
public class StudentId {
    private static final String CLASS_CLASS_NUMBER_REGEX = "^[0-9]{2}학번$";
    private String studentId;

    public StudentId(String studentID) {
        validateEmail(studentID);
    }

    protected StudentId() {
    }

    private void validateEmail(String studentId) {
        if(!Pattern.matches(CLASS_CLASS_NUMBER_REGEX, studentId)){
            throw new InvalidStudentIdException(ErrorMessage.INVALID_CLASS_NUMBER_REGEX_EXCEPTION, "유효한 학번이 아닙니다");
        }
        this.studentId = studentId;
    }
}
