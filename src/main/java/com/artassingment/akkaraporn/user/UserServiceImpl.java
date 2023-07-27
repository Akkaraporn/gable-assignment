package com.artassingment.akkaraporn.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserEntity> getAllUsers(int offset, int pageSize) {
        return userRepository.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public UserQueryRepo getUserById(Long id , String role) {

        return userRepository.findUserByFilter(id,role);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        return null;
    }

}
