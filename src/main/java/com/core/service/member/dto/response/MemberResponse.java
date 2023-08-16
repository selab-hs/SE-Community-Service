package com.core.service.member.dto.response;

import com.core.service.member.domain.vo.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponse {
    private Long id;
    private String email;
    private String name;
    private Long grade;
    private String classNumber;
    private RoleType roleType;

    @Builder
    public MemberResponse(Long id, String email, String name,
                          Long grade, String classNumber, RoleType roleType){
        this.id = id;
        this.email = email;
        this.name = name;
        this.grade = grade;
        this.classNumber = classNumber;
        this.roleType = roleType;
    }
}
