package com.project.myshop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String profileImgUrl;
    private String profileImgTumUrl;
    private Boolean isEnabled;

    @Builder
    public Member(Long id, String username, String password, String email, String profileImgUrl, String profileImgTumUrl, Boolean isEnabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
        this.profileImgTumUrl = profileImgTumUrl;
        this.isEnabled = isEnabled;
    }
}
