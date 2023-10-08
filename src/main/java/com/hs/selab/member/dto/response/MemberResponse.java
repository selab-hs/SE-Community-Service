package com.hs.selab.member.dto.response;

import com.hs.selab.member.domain.vo.RoleType;
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
    private String studentId;
    private RoleType roleType;

    @Builder
    public MemberResponse(Long id, String email, String name,
                          Long grade, String studentId, RoleType roleType){
        this.id = id;
        this.email = email;
        this.name = name;
        this.grade = grade;
        this.studentId = studentId;
        this.roleType = roleType;
    }
}
