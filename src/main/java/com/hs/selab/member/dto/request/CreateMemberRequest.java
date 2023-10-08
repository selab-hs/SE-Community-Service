package com.hs.selab.member.dto.request;

import com.hs.selab.member.domain.Member;
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
    private String studentId;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .grade(grade)
                .studentId(studentId)
                .build();
    }
}
