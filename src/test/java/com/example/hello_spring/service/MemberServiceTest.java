package com.example.hello_spring.service;

import com.example.hello_spring.domain.Member;
import com.example.hello_spring.repository.MemberRepository;
import com.example.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 독립적으로 실행하기 위해 분리
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // test 종료 시 마다 실행
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() { // 회원가입
    // given
        Member member = new Member();
        member.setName("hello");

    // when
        Long saveId = memberService.join(member);

    // then
        Member findeMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findeMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // 예외 처리 방법 1
//        try {
//            memberService.join(member2);
//            fail("예외가 발생해야 합니다.");
//        } catch (IllegalStateException e)  {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


        //then
    }

    @Test
    void findeMembers() {
    }

    @Test
    void findOne() {
    }
}