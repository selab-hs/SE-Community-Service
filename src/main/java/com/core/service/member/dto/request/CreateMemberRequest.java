package com.core.service.member.dto.request;

import com.core.service.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberRequest {
    @Email
    @NotNull
    private String memberEmail;

    @NotNull
    private String memberPassword;

    @NotNull
    private String name;

    @NotNull
    private String grade;

    @NotNull
    private String classNumber;

    public Member toEntity(){
        return Member.builder()
                .memberEmail(memberEmail)
                .memberPassword(memberPassword)
                .name(name)
                .grade(grade)
                .classNumber(classNumber)
                .build();
    }
}