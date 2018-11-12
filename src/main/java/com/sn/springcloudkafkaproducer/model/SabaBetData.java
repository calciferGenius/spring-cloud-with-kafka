package com.sn.springcloudkafkaproducer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SabaBetData {

    @Id
    private String betNo;
    private String loginname;
    private Timestamp wagersDate;
    private Double betAmount;
    private Double commissionable;
    private Double payOff;
}
