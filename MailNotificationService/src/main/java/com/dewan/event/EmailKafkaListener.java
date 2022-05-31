package com.dewan.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class EmailKafkaListener {

    private final Logger log = LoggerFactory.getLogger(EmailKafkaListener.class);

    @KafkaListener(topics = "TOPIC", groupId = "FE_Dev")
    public void listenTestTopic( String data ) {
        log.info("Consumed : "+ data);
    }
}
