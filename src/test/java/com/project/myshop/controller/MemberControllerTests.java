package com.project.myshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.myshop.controller.dto.MemberCreateRequest;
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

    @Test
    @DisplayName("회원가입 테스트")
    public void signUpTest() throws Exception {
        MemberCreateRequest memberCreateRequest = MemberCreateRequest.builder()
                        .username("hello")
                                .password("hello")
                                        .email("hello@gmail.com")
                                                .profileImgUrl("imgURl")
                                                        .profileImgTumUrl("imgUrl2").build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(memberCreateRequest);

        System.out.println(json);
        mockMvc.perform(MockMvcRequestBuilders.post("/member")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
