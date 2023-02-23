package com.project.myshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.myshop.controller.dto.MemberCreateRequest;
import com.project.myshop.domain.Member;
import com.project.myshop.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MemberControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 테스트")
    public void signUpTest() throws Exception {
        MemberCreateRequest memberCreateRequest2 = MemberCreateRequest.builder()
                .username("hello234321")
                .password("hello234!")
                .email("hello234@gmail.com")
                .passwordIsCorrect(true)
                .isIdCorrect(true)
                .isIdDuplicate(false)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(memberCreateRequest2);

        System.out.println(json);
        mockMvc.perform(MockMvcRequestBuilders.post("/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("회원가입 실패")
    public void signUpFail() throws Exception {
        MemberCreateRequest memberCreateRequest = MemberCreateRequest.builder()
                .username("helloworld")
                .password("helloworld130!")
                .email("hello@gmail.com")
                .profileImgUrl("url")
                .profileImgTumUrl("hellll")
                .isIdDuplicate(false)
                .isIdCorrect(true)
                .passwordIsCorrect(true)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(memberCreateRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("닉네임 중복 체크 - 중복")
    public void checkDuplicate() throws Exception {
        Member member = Member.builder()
                .id(1L)
                .username("hello")
                .password("hello")
                .email("hello")
                .build();
        memberRepository.save(member);

        mockMvc.perform(MockMvcRequestBuilders.get("/member/checkId/hello"))
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("닉네임 중복 체크 - 중복x")
    public void checkDuplicate2() throws Exception {
        Member member = Member.builder()
                .id(1L)
                .username("hello")
                .password("hello")
                .email("hello")
                .build();
        memberRepository.save(member);

        mockMvc.perform(MockMvcRequestBuilders.get("/member/checkId/hello2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("회원가입 중복 체크 x")
    public void checkValidation() throws Exception {
        MemberCreateRequest memberCreateRequest = MemberCreateRequest
                .builder()
                .username("hello234")
                .password("hello234!")
                .email("hello234@gmail.com")
                .isIdDuplicate(true)
                .isIdCorrect(true)
                .passwordIsCorrect(true)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(memberCreateRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
}
