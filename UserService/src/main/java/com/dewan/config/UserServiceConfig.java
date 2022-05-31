package com.dewan.config;

import com.dewan.consumer.EventConsumer;
import com.dewan.event.TransactionEvent;
import com.dewan.event.UserRegistrationEvent;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class UserServiceConfig {
    private final EventConsumer<TransactionEvent> transactionEventConsumer;

    @Autowired
    public UserServiceConfig(EventConsumer<TransactionEvent> transactionEventConsumer) {
        this.transactionEventConsumer = transactionEventConsumer;
    }

    @Bean
    public Sinks.Many<UserRegistrationEvent> sink() {
        return Sinks.many()
                .multicast()
                .directBestEffort();
    }

    @Bean
    public Supplier<Flux<UserRegistrationEvent>> userRegistrationEventPublisher(
            Sinks.Many<UserRegistrationEvent> publisher) {
        return publisher::asFlux;
    }

    @Bean
    public Consumer<TransactionEvent> transactionEventProcessor() {
        return transactionEventConsumer::consumeEvent;
    }
}
