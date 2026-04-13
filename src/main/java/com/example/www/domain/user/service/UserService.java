package com.example.www.domain.user.service;

import com.example.www.domain.user.dto.UserRequestDTO;
import com.example.www.domain.user.dto.UserResponseDTO;
import com.example.www.domain.user.entity.UserEntity;
import com.example.www.domain.user.entity.UserRoleType;
import com.example.www.domain.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // User 생성 (Create)
    @Transactional
    public void createOneUser(UserRequestDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String nickname = dto.getNickname();

        // 생성하기 전, 동일한 username이 있는지 확인
        if(userRepository.existsByUsername(username)) {
            return;
        }

        // User 엔티티 생성
        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        entity.setUsername(bCryptPasswordEncoder.encode(password));
        entity.setNickname(nickname);
        entity.setRole(UserRoleType.USER);

        // User 엔티티 저장소에 저장
        userRepository.save(entity);
    }


    // 단일 User 조회 (Read)
    @Transactional(readOnly = true)
    public UserResponseDTO readOneUser(String username) {

        UserEntity entity = userRepository.findByUsername(username).orElseThrow();

        UserResponseDTO dto = new UserResponseDTO();
        dto.setUsername(entity.getUsername());
        dto.setNickname(entity.getNickname());
        dto.setRole(entity.getRole().toString());

        return dto;
    }
}
