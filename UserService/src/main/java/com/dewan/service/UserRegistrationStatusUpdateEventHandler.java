package com.dewan.service;


import com.dewan.entity.UserEntity;
import com.dewan.enums.DefaultMailListStatus;
import com.dewan.enums.UserRegistrationStatus;
import com.dewan.repository.UserRepository;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class UserRegistrationStatusUpdateEventHandler {

    private final Logger log = LoggerFactory.getLogger(UserRegistrationStatusUpdateEventHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRegistrationStatusPublisher userRegistrationStatusPublisher;

    @Transactional
    public void updateUser(long userid, Consumer<UserEntity> consumer){
        this.userRepository
                .findById(userid)
                .ifPresent(consumer.andThen(this::updateUser));

    }
    private void updateUser(UserEntity userEntity){
        if(Objects.isNull(Objects.isNull(userEntity.getDefaultMailListStatus())))
            return;
        var isComplete = DefaultMailListStatus.APPROVED.equals(userEntity.getDefaultMailListStatus());
        var userStatus = isComplete ? UserRegistrationStatus.COMPLETED : UserRegistrationStatus.FAILED;
        userEntity.setUserRegistrationStatus(userStatus);
        if (!isComplete){
            log.info("user registration status: "+isComplete);
            userEntity.setUserRegistrationStatus(UserRegistrationStatus.FAILED);
            this.userRegistrationStatusPublisher.raiseUserRegistrationEvent(userEntity, userStatus);
        }
    }
}
