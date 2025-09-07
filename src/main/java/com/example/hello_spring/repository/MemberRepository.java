package com.example.hello_spring.repository;

import com.example.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 저장

    Optional<Member> findById(Long id);
    // Java8 기능, null 처리 시 보통 사용
    Optional<Member> findByName(String name);
    List<Member> findAll(); // 모든 회원 list 반환
}
