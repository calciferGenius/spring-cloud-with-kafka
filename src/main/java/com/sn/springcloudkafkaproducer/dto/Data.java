package com.sn.springcloudkafkaproducer.dto;

import java.util.List;

@lombok.Data
public class Data {

    private Long last_version_key;
    private List<RawBetDetails> BetDetails;
    private List<RawBetDetails> BetNumberDetails;
    private List<RawBetDetails> BetVirtualSportDetails;

}
