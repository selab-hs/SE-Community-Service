package com.core.service.auth.domain;

import com.core.service.member.domain.Member;
import com.core.service.member.domain.vo.RoleType;
import lombok.Getter;

@Getter
public class UserDetail {
    private Long id;
    private String userEmail;
    private String userPassword;
    private String name;
    private String grade;
    private String classNumber;
    private RoleType roleType;

    public UserDetail(Member member) {
        this.id = member.getId();
        this.userEmail = member.getEmail();
        this.userPassword = member.getPassword();
        this.name = member.getName();
        this.grade = member.getGrade();
        this.classNumber = member.getClassNumber();
        this.roleType = member.getRoleType();
    }
}
