package com.project.myshop.service;

import com.project.myshop.controller.dto.MemberLoginRequest;
import com.project.myshop.domain.Member;
import com.project.myshop.dto.TestMapperDto;
import com.project.myshop.mapper.TestMapper;
import com.project.myshop.repository.MemberRepository;
import com.project.myshop.repository.MemberRepositoryTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class MemberServiceTests {

    @Autowired private MemberRepository memberRepository;

    @Autowired private MemberService memberService;

    @Autowired private TestMapper testMapper;

    @Test
    public void saveAndRead() {
        Member member = Member.builder()
                .id(1L)
                .username("hello")
                .password("hello")
                .email("hello")
                .isEnabled(true)
                .profileImgUrl("hello")
                .profileImgTumUrl("hello")
                .build();

        memberRepository.save(member);
        List<TestMapperDto> list = testMapper.getMemberList();
        TestMapperDto member2 = list.get(0);
        Assertions.assertThat(member.getUsername()).isEqualTo(member2.getUsername());
    }

    @Test
    @DisplayName("로그인")
    public void loginAuth() {
        Member member = Member.builder()
                .username("hello")
                .password("hello")
                .email("hello")
                .isEnabled(true)
                .build();

        memberRepository.save(member);

        MemberLoginRequest memberLoginRequest = MemberLoginRequest.builder()
                .username("hello")
                .password("hello")
                .build();

        Boolean response = memberService.loginAuth(memberLoginRequest);

        Assertions.assertThat(response).isEqualTo(true);
    }

}
