package com.core.service.auth.domain;
import com.core.service.member.domain.vo.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Authentication {
    private UserDetail userDetail;
    private RoleType roleType;
}
