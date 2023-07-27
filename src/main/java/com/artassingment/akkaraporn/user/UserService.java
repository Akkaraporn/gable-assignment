package com.artassingment.akkaraporn.user;

import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    Page<UserEntity> getAllUsers(int offset, int pageSize);
    UserQueryRepo getUserById(Long id, String Role);
    UserDTO createUser(UserDTO userDTO);
}
