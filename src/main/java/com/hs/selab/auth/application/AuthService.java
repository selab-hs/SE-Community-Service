package com.hs.selab.auth.application;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.auth.dto.request.JoinRequest;
import com.hs.selab.auth.token.TokenProvider;
import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.exception.member.InvalidPasswordMatchException;
import com.hs.selab.error.exception.member.NotExistMemberException;
import com.hs.selab.member.domain.vo.Email;
import com.hs.selab.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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
        var member = memberRepository.findByEmail(new Email(joinRequest.getEmail()))
                .orElseThrow(
                        () -> new NotExistMemberException(ErrorMessage.NOT_EXIST_MEMBER_EXCEPTION, "해당 유저 정보가 존재하지 않습니다.")
                );

        if(!passwordEncoder.matches(joinRequest.getPassword(), member.getPassword())) {
            throw new InvalidPasswordMatchException(ErrorMessage.INVALID_PASSWORD_MATCH_EXCEPTION, "잘못된 비밀번호 입니다");
        }

        return tokenProvider.generateJwtToken(member.getId(), member.getRoleType().getRole());
    }

}
