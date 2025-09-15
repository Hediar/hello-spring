package com.example.hello_spring.repository;

import com.example.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id 세팅
        store.put(member.getId(), member); // store 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null 가능성
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 하나라도 있으면 반환

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 멤버들 반환
    }

    public void clearStore() {
        store.clear();
    }
}
