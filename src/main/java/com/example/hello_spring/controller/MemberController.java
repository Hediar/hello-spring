package com.example.hello_spring.controller;

import com.example.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller // spring 컨테이너에서 스프링 빈 관리
public class MemberController {
    private final MemberService memberService;

    @Autowired // 생성자 호출 시, 스프링 컨테이너에서 맴버 서비스를 연결해준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
