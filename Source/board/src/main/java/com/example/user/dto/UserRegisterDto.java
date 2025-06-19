package com.example.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterDto {
    private String username;  // 이름
    private String userId;    // 아이디
    private String password;  // 비밀번호
    private String email;     // 이메일
}
