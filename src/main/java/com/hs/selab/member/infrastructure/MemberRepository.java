package com.hs.selab.member.infrastructure;

import com.hs.selab.member.domain.Member;
import com.hs.selab.member.domain.vo.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(Email email);
}
