package com.dewan.processor;

import com.dewan.entity.UserEntity;
import com.dewan.event.UserRegistrationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
public class UserRegistrationProcessor {

    private final Sinks.Many<UserRegistrationEvent> sink;

    @Autowired
    public UserRegistrationProcessor(Sinks.Many<UserRegistrationEvent> sink)
    {
        this.sink = sink;
    }

    public void process(UserEntity userEntity) {
        UserRegistrationEvent userRegistrationEvent = new UserRegistrationEvent()
                .setUserid(userEntity.getUserid())
                .setUsername(userEntity.getUsername())
                .setPassword(userEntity.getPassword())
                .setEmail(userEntity.getEmail());
        sink.emitNext(userRegistrationEvent, Sinks.EmitFailureHandler.FAIL_FAST);
    }


}
