package com.example.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResDto {
    private String userId;
    private String email;
    private boolean admin;
}
