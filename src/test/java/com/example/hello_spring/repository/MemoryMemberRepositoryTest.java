package com.example.hello_spring.repository;

import com.example.hello_spring.domain.Member;
//import org.junit.jupiter.api.Assertions; // 19번줄
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

//    test 종료 시 마다 repository 지우는 기능 필요 => 테스트별 의존 관계 삭제
    @AfterEach // test 종료 시 마다 실행
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // test 코드라 get으로 확인
        System.out.println("result" + result); // 출력 확인방법1
//        Assertions.assertEquals(member, result); // 예상 결과랑 같은지 확인 (기대한값, 실제값) 비교
//        Assertions.assertThat(member).isEqualTo(result); // 비교방법 3
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
