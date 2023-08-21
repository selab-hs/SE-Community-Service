package com.core.service.member.application;

import com.core.service.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncoderService {
    private final PasswordEncoder passwordEncoder;

    public Member encodePassword(Member member) {
        member.encodePassword(encoder(member.getPassword().getPassword()));
        return member;
    }

    private String encoder(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean passwordMatch(String password, String DBPassword) {
        return passwordEncoder.matches(password, DBPassword);
    }
}