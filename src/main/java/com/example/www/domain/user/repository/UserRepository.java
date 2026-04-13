package com.example.www.domain.user.repository;

import com.example.www.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // UserRepository에서 DB에 username이 있는지 확인하기 위한 메서드
    Boolean existsByUsername(String username);

    // UserRepository에서 username을 통해 DB에 존재하는 username을 조회하기 위한 메서드
    Optional<UserEntity> findByUsername(String username);
}
