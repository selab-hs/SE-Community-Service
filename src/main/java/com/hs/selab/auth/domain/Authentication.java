package com.hs.selab.auth.domain;
import com.hs.selab.member.domain.vo.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Authentication {
    private UserDetail userDetail;
    private RoleType roleType;
}
