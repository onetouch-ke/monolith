package com.example.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.users.dto.LogInDto;
import com.example.users.dto.LoginResDto;
import com.example.users.dto.UserRegisterDto;
import com.example.users.entity.UserEntity;
import com.example.users.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 전체 회원 목록 조회
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    // 로그인 ID 기준 단일 회원 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getUserByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

    // 회원가입
    @PostMapping("/register")
    public UserEntity register(@RequestBody UserRegisterDto dto) {
        return userService.resgister(dto);
    }

    // 로그인
    @PostMapping("/login")
    public LoginResDto login(@RequestBody LogInDto dto) {
        return userService.login(dto);
    }

    // 회원 정보 수정
    @PutMapping("/{userId}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable String userId, @RequestBody UserRegisterDto dto) {
        UserEntity updatedUser = userService.updateUser(userId, dto);
        return ResponseEntity.ok(updatedUser);
    }

    // 회원 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable String userId) {
        UserEntity deletedUser = userService.deleteUser(userId);
        return ResponseEntity.ok(deletedUser);
    }
}
