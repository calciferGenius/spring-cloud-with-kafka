package com.sn.springcloudkafkaproducer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CashoutData {

    private Long cashout_id;
    private String transdate;
    private Double stake;
    private Double winlost_amount;
    private Double buyback_amount;
    private Double real_stake;
    private Integer bet_type;
    private String status;
    private String winlost_datetime;
    private Integer product_id;
    private Date trans_datetime;
    private Date winloss_date;
    private Long trans_id_ref;
    private String member;
}
