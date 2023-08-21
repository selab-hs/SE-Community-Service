package com.core.service.auth.application;

import com.core.service.auth.domain.UserDetail;
import com.core.service.auth.dto.request.JoinRequest;
import com.core.service.auth.token.TokenProvider;
import com.core.service.error.dto.ErrorMessage;
import com.core.service.error.exception.member.InvalidPasswordMatchException;
import com.core.service.error.exception.member.NotExistMemberException;
import com.core.service.member.application.EncoderService;
import com.core.service.member.domain.Member;
import com.core.service.member.domain.vo.Email;
import com.core.service.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final EncoderService encoderService;

    /**
     * @brief      * 유저 조회
     * @param      * id : 파싱한 token Long userID
     * @return     * userId 조회를 통한 UserDetail
     */
    @Transactional(readOnly = true)
    public UserDetail loadUserById(Long id) {
        var member =  memberRepository.findById(id)
                .orElseThrow(() -> new NotExistMemberException(ErrorMessage.NOT_EXIST_MEMBER_EXCEPTION, "해당 유저 정보가 존재하지 않습니다."));

        return new UserDetail(member);
    }

    /**
     * @brief      * 유저 로그인
     * @param      * JoinRequest : 로그인을 위한 email, password
     * @return     * jwt token : 조회한 유저 정보 id, role 이용한 jwt token
     */
    @Transactional(readOnly = true)
    public String userLogin(JoinRequest joinRequest){
        Member member = memberRepository.findByEmail(new Email(joinRequest.email()))
                .orElseThrow(
                        () -> new NotExistMemberException(ErrorMessage.NOT_EXIST_MEMBER_EXCEPTION, "해당 유저 정보가 존재하지 않습니다.")
                );

        if(!encoderService.passwordMatch(joinRequest.password(), member.getPassword().getPassword())) {
            throw new InvalidPasswordMatchException(ErrorMessage.INVALID_PASSWORD_MATCH_EXCEPTION, "잘못된 비밀번호 입니다");
        }

        return tokenProvider.generateJwtToken(member.getId(), member.getRoleType().getRole());
    }

}