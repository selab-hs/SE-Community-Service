package com.core.service.member.application;

import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.member.AlreadyExistMemberEmailException;
import com.core.service.member.domain.Member;
import com.core.service.member.dto.request.CreateMemberRequest;
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

    @Transactional(readOnly = true)
    public Member findMemberByID(Long id) {
        return memberRepository.findById(id).get();
    }

    @Transactional
    public MemberResponse joinMember(CreateMemberRequest request) {
        duplicateValidationMemberEmail(request.getMemberEmail());
        var response = memberRepository.save(request.toEntity());

        return response.toResponseDto();
    }


    public List<MemberResponse> searchAllMember() {
        var response = memberRepository.findAll().stream()
                .map(member -> member.toResponseDto())
                .collect(Collectors.toList());
        return response;
    }

    @Transactional(readOnly = true)
    public void duplicateValidationMemberEmail(String email){
        memberRepository.findByMemberEmail(email)
                .ifPresent(member -> {
                    throw new AlreadyExistMemberEmailException(ErrorMessage.ALREADY_EXIST_MEMBER_EMAIL_EXCEPTION, "이미 존재하는 이메일 정보입니다");
                });
    }
}
