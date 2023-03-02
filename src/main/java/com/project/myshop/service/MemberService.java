package com.project.myshop.service;

import com.project.myshop.controller.dto.MemberCreateRequest;
import com.project.myshop.controller.dto.MemberImgResponse;
import com.project.myshop.controller.dto.MemberLoginRequest;
import com.project.myshop.domain.Member;
import com.project.myshop.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void signUp(MemberCreateRequest memberCreateRequest) {
        Member member = toMemberFromRequest(memberCreateRequest);
        memberRepository.save(member);
    }

    public Boolean isDuplicate(String username) {
        return memberRepository.existsByUsername(username);
    }

    public Boolean loginAuth(MemberLoginRequest memberLoginRequest) {
        Boolean response = memberRepository.existsByUsernameAndPassword(memberLoginRequest.getUsername(), memberLoginRequest.getPassword());
        return response;
    }

    public static Member toMemberFromRequest(MemberCreateRequest memberCreateRequest){
        return Member.builder()
                .username(memberCreateRequest.getUsername())
                .password(memberCreateRequest.getPassword())
                .email(memberCreateRequest.getEmail())
                .profileImgUrl(memberCreateRequest.getProfileImgUrl())
                .profileImgTumUrl(memberCreateRequest.getProfileImgTumUrl())
                .isEnabled(true)
                .build();
    }
}
