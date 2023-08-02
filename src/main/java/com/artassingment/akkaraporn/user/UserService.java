package com.artassingment.akkaraporn.user;

import org.springframework.data.domain.Page;

public interface UserService {
    Page<UserEntity> getAllUsers(int offset, int pageSize);
    UserQueryRepo getUserById(Long id, String Role);
    UserDTO createUser(UserDTO userDTO);

}
