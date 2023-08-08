package com.core.service.member.domain;

import com.core.service.common.domain.BaseEntity;
import com.core.service.member.domain.vo.RoleType;
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
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String name;

    @Column
    private String grade;

    @Column
    private String classNumber;

    @Column
    @Enumerated
    private RoleType roleType;

    @Builder
    public Member(String memberEmail, String memberPassword, String name,
                  String grade, String classNumber){
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.name = name;
        this.grade = grade;
        this.classNumber = classNumber;
        this.roleType = RoleType.LAB_USER;
    }

    public MemberResponse toResponseDto(){
        return MemberResponse.builder()
                .id(id)
                .email(memberEmail)
                .name(name)
                .grade(grade)
                .classNumber(classNumber)
                .roleType(roleType)
                .build();
    }
}
