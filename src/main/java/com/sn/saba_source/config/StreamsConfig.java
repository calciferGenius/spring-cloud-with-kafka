package com.sn.saba_source.config;

import com.sn.saba_source.stream.SabaStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;

//@EnableBinding(GreetingsStreams.class) //for single bindings
@EnableBinding(value = {SabaStreams.class})//GreetingsStreams.class,
public class StreamsConfig {

}
