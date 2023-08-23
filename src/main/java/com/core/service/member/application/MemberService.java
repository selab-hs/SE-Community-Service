package com.core.service.member.application;

import com.core.service.auth.domain.UserDetail;
import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.member.AlreadyExistMemberEmailException;
import com.core.service.error.exception.member.InvalidLoginInfoException;
import com.core.service.error.exception.member.LoginTokenNullException;
import com.core.service.member.domain.Member;
import com.core.service.member.domain.vo.Email;
import com.core.service.member.domain.vo.RoleType;
import com.core.service.member.dto.request.CreateMemberRequest;
import com.core.service.member.dto.request.UpdateMemberRequest;
import com.core.service.member.dto.response.MemberResponse;
import com.core.service.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse joinMember(CreateMemberRequest request) {
        duplicateValidationMemberEmail(request.getEmail());
        var response = memberRepository.save(request.toEntity());

        return response.toResponseDto();
    }

    public List<MemberResponse> searchAllMember() {
        return memberRepository.findAll().stream()
                .map(Member::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public MemberResponse updateMember(UserDetail detail, UpdateMemberRequest request) {
        loginCheckException(detail);
        var member = findByIdFromLogin(detail.getId());
        member.updateMember(request);
        memberRepository.save(member);

        return member.toResponseDto();
    }


    @Transactional(readOnly = true)
    public void duplicateValidationMemberEmail(String email){
        memberRepository.findByEmail(new Email(email))
                .ifPresent(member -> {
                    throw new AlreadyExistMemberEmailException(ErrorMessage.ALREADY_EXIST_MEMBER_EMAIL_EXCEPTION, "이미 존재하는 이메일 정보입니다");
                });
    }

    @Transactional(readOnly = true)
    public Member findByIdFromLogin(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new InvalidLoginInfoException(ErrorMessage.INVALID_LOGIN_USER_INFORMATION_EXCEPTION, "잘못된 유저 로그인 정보입니다"));
    }

    private void loginCheckException(UserDetail detail) {
        if(detail.getRoleType() == RoleType.GUEST) {
            throw new LoginTokenNullException(ErrorMessage.NOT_LOGIN_USER_EXCEPTION, "로그인 정보가 없습니다");
        }
    }
}
