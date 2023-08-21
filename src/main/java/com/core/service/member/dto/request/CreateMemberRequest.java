package com.core.service.member.dto.request;

import com.core.service.member.domain.Member;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
public class CreateMemberRequest {
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    @Min(1)
    @Max(4)
    private Long grade;

    @NotNull
    private String classNumber;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .grade(grade)
                .classNumber(classNumber)
                .build();
    }
}