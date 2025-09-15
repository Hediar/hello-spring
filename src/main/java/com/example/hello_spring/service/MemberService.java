package com.example.hello_spring.service;

import com.example.hello_spring.domain.Member;
import com.example.hello_spring.repository.MemberRepository;
import com.example.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { // 외부에서 생성 DI
        this.memberRepository = memberRepository;
    }

    /**
     *
     * 회원가입
     */
    public Long join(Member member) {
        // 이름이 같은 중복 회원 x
//        Optional<Member> result = memberRepository.findByName(member.getName()); // 반환 확인 변수 1번 방법
//        result.ifPresent(member1 -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        // 반환 확인 변수 2번 방법
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
        전체 회원 조회
     */
    public List<Member> findeMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
