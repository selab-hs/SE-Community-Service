package com.hs.selab.auth.domain;

import com.hs.selab.member.domain.Member;
import com.hs.selab.member.domain.vo.MemberState;
import com.hs.selab.member.domain.vo.RoleType;
import lombok.Getter;

@Getter
public class UserDetail {
    private Long id;
    private String userEmail;
    private String name;
    private Long grade;
    private String studentID;
    private MemberState state;
    private RoleType roleType;

    public UserDetail(Member member) {
        this.id = member.getId();
        this.userEmail = member.getEmail().getEmail();
        this.name = member.getName().getName();
        this.grade = member.getGrade();
        this.studentID = member.getStudentId().getStudentId();
        this.state = member.getState();
        this.roleType = member.getRoleType();
    }

    public UserDetail() {
        this.roleType = RoleType.GUEST;
    }
}
