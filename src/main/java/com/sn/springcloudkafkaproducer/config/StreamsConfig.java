package com.sn.springcloudkafkaproducer.config;

import com.sn.springcloudkafkaproducer.stream.GreetingsStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(GreetingsStreams.class)
public class StreamsConfig {

}
