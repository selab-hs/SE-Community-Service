package com.hs.selab.member.domain;

import com.hs.selab.common.domain.BaseEntity;
import com.hs.selab.member.domain.converter.PasswordEncodeConverter;
import com.hs.selab.member.domain.vo.*;
import com.hs.selab.member.dto.request.UpdateMemberRequest;
import com.hs.selab.member.dto.response.MemberResponse;
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
    @Convert(converter=PasswordEncodeConverter.class)
    private String password;

    @Column
    @Embedded
    private Name name;

    @Column
    private Long grade;

    @Column
    @Embedded
    private StudentId studentId;

    @Column
    @Enumerated
    private RoleType roleType;

    @Column
    @Enumerated
    private MemberState state;

    @Builder
    public Member(String email, String password, String name,
                  Long grade, String studentId){
        this.email = new Email(email);
        this.password = password;
        this.name = new Name(name);
        this.grade = grade;
        this.studentId = new StudentId(studentId);
        this.state = MemberState.ENROLLED;
        this.roleType = RoleType.USER;
    }

    public MemberResponse toResponseDto(){
        return MemberResponse.builder()
                .id(id)
                .email(email.getEmail())
                .name(name.getName())
                .grade(grade)
                .studentId(studentId.getStudentId())
                .state(this.state)
                .roleType(roleType)
                .build();
    }

    public void updateMember(UpdateMemberRequest request) {
        this.email = new Email(request.getEmail());
        this.password = request.getPassword();
        this.name = new Name(request.getName());
        this.grade = request.getGrade();
    }

    public void updateRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
