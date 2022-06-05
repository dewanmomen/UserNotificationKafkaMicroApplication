package com.dewan.service;

import com.dewan.controller.UsersAppController;
import com.dewan.dto.UserRegistrationRequestDtO;
import com.dewan.entity.UserEntity;
import com.dewan.enums.UserRegistrationStatus;
import com.dewan.error.CustomGlobalExceptionHandler;
import com.dewan.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRegistrationStatusPublisher publisher;

    @Transactional
    public UserEntity createNewUser(UserEntity userEntityParam){
        log.info("CreateNewUser called form UserService Object");
        UserEntity userEntity = null;
        try {
            entityToDto(userEntityParam);
            userEntityParam.setUserRegistrationStatus(UserRegistrationStatus.CREATED);
            userEntity = userRepository.save(userEntityParam);

            //userEntity = userRepository.save(this.dtoToEntity(userRegistrationRequestDtO));

            this.publisher.raiseUserRegistrationEvent(userEntity, UserRegistrationStatus.CREATED);
        }catch (Exception ex)
        {
            ex.printStackTrace();
            new CustomGlobalExceptionHandler();
        }
        return userEntity;
    }

    private UserEntity dtoToEntity(final UserRegistrationRequestDtO dto){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(dto.getPassword());
        userEntity.setEmail(dto.getEmail());
        userEntity.setUserRegistrationStatus(UserRegistrationStatus.CREATED);
        return userEntity;
    }

    private UserRegistrationRequestDtO entityToDto(UserEntity userEntity){
        UserRegistrationRequestDtO dto = new UserRegistrationRequestDtO();
        dto.setUsername(userEntity.getUsername());
        dto.setPassword(userEntity.getPassword());
        dto.setEmail(userEntity.getEmail());

        return dto;
    }

}
