package com.example.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.users.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    
    // 사용자 아이디(user_id)로 회원 조회
    Optional<UserEntity> findByUserId(String userId);
    boolean existsByUserId(String userId);
    boolean existsByEmail(String email);
}
