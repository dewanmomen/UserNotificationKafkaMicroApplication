package com.dewan.config;

import com.dewan.enums.UserRegistrationStatus;
import com.dewan.events.DefaultMailListEvent;
import com.dewan.events.UserRegistrationEvent;
import com.dewan.service.DefaultMailListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class MailNotificationConfig {

    private final Logger log = LoggerFactory.getLogger(MailNotificationConfig.class);

    @Autowired
    private DefaultMailListService defaultMailListService;

    @Bean
    public Function<Flux<UserRegistrationEvent>, Flux<DefaultMailListEvent>> UserEmailProcessor() {
        return flux -> flux.flatMap(this::processUserEmail);
    }

    private Mono<DefaultMailListEvent> processUserEmail(UserRegistrationEvent event){
        log.info("Receive mail process");
        if(event.getUserRegistrationStatus().equals(UserRegistrationStatus.CREATED)){
            return Mono.fromSupplier(() -> this.defaultMailListService.handleEvent(event));
        }else{
            return Mono.fromRunnable(() -> this.defaultMailListService.cancelEvent(event));
        }
    }
}
