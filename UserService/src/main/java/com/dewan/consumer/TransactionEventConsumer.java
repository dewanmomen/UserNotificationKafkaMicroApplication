package com.dewan.consumer;

import com.dewan.entity.UserEntity;
import com.dewan.event.TransactionEvent;
import com.dewan.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import org.springframework.stereotype.Component;

import static com.dewan.enums.UserRegistrationStatus.COMPLETED;
import static com.dewan.enums.UserRegistrationStatus.FAILED;
import static com.dewan.enums.TransactionStatus.SUCCESSFUL;

@Component
public class TransactionEventConsumer implements EventConsumer<TransactionEvent>{

    private final UserRepository userRepository;

    private final Scheduler jdbcScheduler;

    @Autowired
    public TransactionEventConsumer(UserRepository userRepository, Scheduler jdbcScheduler) {
        this.userRepository = userRepository;
        this.jdbcScheduler = jdbcScheduler;
    }

    public void consumeEvent(TransactionEvent event) {
        Mono.fromRunnable(
                        () -> userRepository.findById(event.getUserid())
                                .ifPresent(user -> {
                                    setStatus(event, user);
                                    userRepository.save(user);
                                }))
                .subscribeOn(jdbcScheduler)
                .subscribe();
    }

    private void setStatus(TransactionEvent transactionEvent, UserEntity userEntity) {
        userEntity.setUserRegistrationStatus(SUCCESSFUL.equals(transactionEvent.getStatus())
                ? COMPLETED
                : FAILED);
    }

}
