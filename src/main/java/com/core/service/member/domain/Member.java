package com.core.service.member.domain;

import com.core.service.common.domain.BaseEntity;
import com.core.service.member.domain.vo.*;
import com.core.service.member.dto.request.UpdateMemberRequest;
import com.core.service.member.dto.response.MemberResponse;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private Long grade;

    @Column
    private String classNumber;

    @Column
    @Enumerated
    private RoleType roleType;

    @Builder
    public Member(String email, String password, String name,
                  Long grade, String classNumber){
        this.email = new Email(email).getEmail();
        this.password = new Password(password).getPassword();
        this.name = new Name(name).getName();
        this.grade = grade;
        this.classNumber = new ClassNumber(classNumber).getClassNumber();
        this.roleType = RoleType.LAB_USER;
    }

    public MemberResponse toResponseDto(){
        return MemberResponse.builder()
                .id(id)
                .email(email)
                .name(name)
                .grade(grade)
                .classNumber(classNumber)
                .roleType(roleType)
                .build();
    }

    public void encodePassword(String encodePassword) {
        this.password = encodePassword;
    }

    public void updateMember(UpdateMemberRequest request) {
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.name = request.getName();
        this.grade = request.getGrade();
    }

    public void updateRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
