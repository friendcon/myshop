package com.project.myshop.service;

import com.project.myshop.domain.Member;
import com.project.myshop.dto.TestMapperDto;
import com.project.myshop.mapper.TestMapper;
import com.project.myshop.repository.MemberRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("prod")
public class MemberServiceTests {

    @Autowired private MemberRepository memberRepository;
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

        List<TestMapperDto> list = testMapper.getMemberList();
        TestMapperDto member2 = list.get(0);
        Assertions.assertThat(member.getUsername()).isEqualTo(member2.getUsername());
    }


}
