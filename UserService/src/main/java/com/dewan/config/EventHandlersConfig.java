package com.dewan.config;

import com.dewan.events.DefaultMailListEvent;
import com.dewan.service.UserRegistrationStatusUpdateEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EventHandlersConfig {

    @Autowired
    private UserRegistrationStatusUpdateEventHandler userEventHandler;

    @Bean
    public Consumer<DefaultMailListEvent> mailEventConsumer(){
        return pe -> {
            userEventHandler.updateUser(pe.getUsersMailListDTO().getUserId(), po -> {
                po.setDefaultMailListStatus(pe.getDefaultMailListStatus());
            });
        };
    }
}
