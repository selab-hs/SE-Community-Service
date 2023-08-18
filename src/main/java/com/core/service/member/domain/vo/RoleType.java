package com.core.service.member.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    GUEST("GUEST", "게스트"),
    LAB_USER("ROLE_USER", "랩원"),
    LAB_MANAGER("ROLE_MANAGER", "랩총무"),
    LAB_LEADER("ROLE_LEADER", "랩장");

    private final String role;
    private final String value;

}
