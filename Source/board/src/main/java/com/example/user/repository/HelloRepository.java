package com.example.user.repository;

import com.example.user.entity.HelloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository extends JpaRepository<HelloEntity, Long> {
}
