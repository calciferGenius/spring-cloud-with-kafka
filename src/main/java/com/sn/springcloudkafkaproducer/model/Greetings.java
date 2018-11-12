package com.sn.springcloudkafkaproducer.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Greetings {

    private long timestamp;
    private String message;
}
