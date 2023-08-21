package com.core.service.auth.domain;
import com.core.service.member.domain.vo.RoleType;

public record Authentication(UserDetail userDetail, RoleType roleType) {
}
