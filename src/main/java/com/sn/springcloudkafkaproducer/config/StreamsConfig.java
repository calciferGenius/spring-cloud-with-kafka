package com.sn.springcloudkafkaproducer.config;

import com.sn.springcloudkafkaproducer.stream.GreetingsStreams;
import com.sn.springcloudkafkaproducer.stream.SabaStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

//@EnableBinding(GreetingsStreams.class) //for single bindings
@EnableBinding(value = {GreetingsStreams.class, SabaStreams.class})
public class StreamsConfig {

}
