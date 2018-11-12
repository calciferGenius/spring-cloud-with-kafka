package com.sn.springcloudkafkaproducer.service;

import com.sn.springcloudkafkaproducer.model.Greetings;
import com.sn.springcloudkafkaproducer.stream.GreetingsStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
@Slf4j
public class GreetingService {

    private final GreetingsStreams greetingsStreams;

    public GreetingService(GreetingsStreams greetingsStreams1) {
        this.greetingsStreams = greetingsStreams1;
    }

    public void sendGreeting(final Greetings greetings) {
        log.info("Sending greetings {}", greetings);
        MessageChannel messageChannel = greetingsStreams.outboundGreetings();
        messageChannel.send(MessageBuilder
                .withPayload(greetings)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
