package com.hs.selab.member.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberState {
    ENROLLED("재학생"),
    ON_LEAVE("휴학생"),
    GRADUATED( "졸업생"),
    LAB_LEADER("랩장");

    private final String state;
}
