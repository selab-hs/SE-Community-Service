package com.core.service.member.domain;

import com.core.service.common.domain.BaseEntity;
import com.core.service.member.domain.vo.*;
import com.core.service.member.dto.request.UpdateMemberRequest;
import com.core.service.member.dto.response.MemberResponse;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Embedded
    private Email email;

    @Column
    @Embedded
    private Password password;

    @Column
    @Embedded
    private Name name;

    @Column
    private Long grade;

    @Column
    @Embedded
    private ClassNumber classNumber;

    @Column
    @Enumerated
    private RoleType roleType;

    @Builder
    public Member(String email, String password, String name,
                  Long grade, String classNumber){
        this.email = new Email(email);
        this.password = new Password(password);
        this.name = new Name(name);
        this.grade = grade;
        this.classNumber = new ClassNumber(classNumber);
        this.roleType = RoleType.LAB_USER;
    }

    public MemberResponse toResponseDto(){
        return MemberResponse.builder()
                .id(id)
                .email(email.getEmail())
                .name(name.getName())
                .grade(grade)
                .classNumber(classNumber.getClassNumber())
                .roleType(roleType)
                .build();
    }

    public void encodePassword(String encodePassword) {
        this.password.setEncodePassword(encodePassword);
    }

    public void updateMember(UpdateMemberRequest request) {
        this.email = new Email(request.getEmail());
        this.password = new Password(request.getPassword());
        this.name = new Name(request.getName());
        this.grade = request.getGrade();
    }

    public void updateRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
