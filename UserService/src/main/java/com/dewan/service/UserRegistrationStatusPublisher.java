package com.dewan.service;

import com.dewan.dto.UserRegistrationDto;
import com.dewan.entity.UserEntity;
import com.dewan.enums.UserRegistrationStatus;
import com.dewan.events.UserRegistrationEvent;
import com.dewan.repository.UserRepository;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class UserRegistrationStatusPublisher {

    private final Logger log = LoggerFactory.getLogger(UserRegistrationStatusPublisher.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Sinks.Many<UserRegistrationEvent> userSink;

    public void raiseUserRegistrationEvent(UserEntity userEntity, UserRegistrationStatus userRegistrationStatus) {
        log.info("raise User Registration and user registration status: " + userRegistrationStatus);
        var dto = UserRegistrationDto.of(
                    userEntity.getUserid(),
                    userEntity.getUsername(),
                    userEntity.getPassword(),
                    userEntity.getEmail()
            );
            var userRegistrationEvent = new UserRegistrationEvent(dto, userRegistrationStatus);
            this.userSink.tryEmitNext(userRegistrationEvent);

            if (userRegistrationStatus.equals(UserRegistrationStatus.FAILED)) {
                userEntity.setUserRegistrationStatus(UserRegistrationStatus.FAILED);
                //userRepository.delete(userEntity);
            }

//        if (userRegistrationStatus.equals(UserRegistrationStatus.FAILED)) {
//            //userEntity.setUserRegistrationStatus(UserRegistrationStatus.FAILED);
//            userRepository.delete(userEntity);
//        }
//        else if (userRegistrationStatus.equals(UserRegistrationStatus.CREATED)){
//            var dto = UserRegistrationDto.of(
//                    userEntity.getUserid(),
//                    userEntity.getUsername(),
//                    userEntity.getPassword(),
//                    userEntity.getEmail()
//            );
//            var userRegistrationEvent = new UserRegistrationEvent(dto, userRegistrationStatus);
//            this.userSink.tryEmitNext(userRegistrationEvent);
//        }
    }
}
