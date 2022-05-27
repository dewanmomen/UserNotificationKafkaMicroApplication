package com.dewan.service;

import com.dewan.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    // Save User
    UserEntity saveUser(UserEntity userEntity);
}
