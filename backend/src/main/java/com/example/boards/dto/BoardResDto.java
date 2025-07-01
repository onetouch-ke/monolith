package com.example.boards.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class BoardResDto {
    private Long id;
    private String title;
    private String content;
    private String authorUsername;
}