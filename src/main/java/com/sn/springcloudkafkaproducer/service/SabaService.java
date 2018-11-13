package com.sn.springcloudkafkaproducer.service;

import com.sn.springcloudkafkaproducer.model.SabaBetData;
import com.sn.springcloudkafkaproducer.stream.SabaStreams;
import com.sn.springcloudkafkaproducer.utils.BaseCommunicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

/**
 * Processor APP
 *
 */
@Service
@Slf4j
public class SabaService extends BaseCommunicate{

    private final SabaStreams sabaStreams;

    public SabaService(SabaStreams sabaStreams) {
        this.sabaStreams = sabaStreams;
    }

    public void sendBetData(final SabaBetData sabaBetData) {
        log.info("Sending saba bet data {}", sabaBetData);
        MessageChannel messageChannel = sabaStreams.outboundTestData();
        messageChannel.send(MessageBuilder
                .withPayload(sabaBetData)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
