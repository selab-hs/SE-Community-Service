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
    private Long grade;
    private String studentID;
    private RoleType roleType;

    public UserDetail(Member member) {
        this.id = member.getId();
        this.userEmail = member.getEmail().getEmail();
        this.userPassword = member.getPassword();
        this.name = member.getName().getName();
        this.grade = member.getGrade();
        this.studentID = member.getStudentId().getStudentId();
        this.roleType = member.getRoleType();
    }

    public UserDetail() {
        this.roleType = RoleType.GUEST;
    }
}
