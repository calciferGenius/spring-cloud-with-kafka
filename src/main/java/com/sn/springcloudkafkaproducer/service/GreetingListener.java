//package com.sn.springcloudkafka.service;
//
//import com.sn.springcloudkafka.model.Greetings;
//import com.sn.springcloudkafka.stream.GreetingsStreams;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Component;
//
//@Component
//@Slf4j
//public class GreetingListener {
//
//    @StreamListener(GreetingsStreams.INPUT)
//    public void handleGreetings(@Payload Greetings greetings) {
//        log.info("Received greetings: {}", greetings);
//    }
//
//}
