package com.example.www.domain.user.repository;

import com.example.www.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // UserRepository에서 DB에 username이 있는지 확인하기 위한 메서드
    Boolean existsByUsername(String username);
}
