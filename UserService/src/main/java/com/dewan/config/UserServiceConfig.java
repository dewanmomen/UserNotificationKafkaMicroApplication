package com.dewan.config;

import com.dewan.events.UserRegistrationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class UserServiceConfig {


    @Bean
    public Sinks.Many<UserRegistrationEvent> userSink() {
//        return Sinks.many()
//                .multicast()
//                .directBestEffort();

        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<UserRegistrationEvent>> userRegistrationEventPublisher(Sinks.Many<UserRegistrationEvent> publisher){
        return publisher::asFlux;
    }

//    @Bean
//    public Supplier<Flux<UserRegistrationEvent>> userRegistrationEventPublisher(
//            Sinks.Many<UserRegistrationEvent> publisher) {
//        return publisher::asFlux;
//    }

}
