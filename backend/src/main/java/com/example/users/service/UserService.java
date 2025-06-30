package com.example.users.service;

import org.springframework.stereotype.Service;

import com.example.users.dto.LogInDto;
import com.example.users.dto.LoginResDto;
import com.example.users.dto.UserRegisterDto;
import com.example.users.entity.UserEntity;
import com.example.users.exception.EmailAlreadyExistsException;
import com.example.users.exception.UserIdAlreadyExistsException;
import com.example.users.repository.UserRepository;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 전체 회원 목록 조회
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // 로그인 ID(userId) 기준 단일 회원 조회
    public UserEntity getUserByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }

    // 회원가입
    public UserEntity resgister(UserRegisterDto dto) {
     if (userRepository.existsByUserId(dto.getUserId())) {
            throw new UserIdAlreadyExistsException("이미 존재하는 아이디입니다.");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("이미 존재하는 이메일입니다.");
        }

    UserEntity user = UserEntity.builder()
            .username(dto.getUsername())
            .userId(dto.getUserId())
            .password(dto.getPassword())
            .email(dto.getEmail())
            .build();

    return userRepository.save(user);
}

    // 회원 정보 수정 (userId 기준)
    public UserEntity updateUser(String userId, UserRegisterDto dto) {
        UserEntity user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        user.setUsername(dto.getUsername());
        user.setUserId(dto.getUserId());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());

        return userRepository.save(user);
    }

    // 로그인
    public LoginResDto login(LogInDto dto) {
    UserEntity user = userRepository.findByUserId(dto.getUserId())
            .orElseThrow(() -> new RuntimeException("존재하지 않는 아이디입니다."));

    if (!user.getPassword().equals(dto.getPassword())) {
        throw new RuntimeException("비밀번호가 일치하지 않습니다.");
    }

    boolean isAdmin = "root".equals(user.getUserId());

    return new LoginResDto(user.getUserId(), user.getEmail(), isAdmin);
}



    // 회원 삭제 (userId 기준)
    public UserEntity deleteUser(String userId) {
        UserEntity user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        userRepository.delete(user);
        return user;
    }
}
