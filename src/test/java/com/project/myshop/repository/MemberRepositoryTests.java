package com.project.myshop.repository;

import com.project.myshop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 테스트")
    public void signUp() {
        Member member = Member.builder()
                .username("hello0218")
                .password("hello0218")
                .email("hello0218@gmail.com")
                .build();

        memberRepository.save(member);
    }

    @Test
    @DisplayName("아이디 중복 체크를 위한 것")
    public void existCheck() {
        Member member = Member.builder()
                        .id(1L)
                                .username("hello")
                                        .password("hello")
                                                .email("hello")
                                                        .build();
        memberRepository.save(member);

        Boolean response = memberRepository.existsByUsername("hello");

        Assertions.assertThat(response).isEqualTo(true);
    }
}
