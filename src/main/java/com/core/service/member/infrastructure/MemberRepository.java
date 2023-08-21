package com.core.service.member.infrastructure;

import com.core.service.member.domain.Member;
import com.core.service.member.domain.vo.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(Email email);
}
