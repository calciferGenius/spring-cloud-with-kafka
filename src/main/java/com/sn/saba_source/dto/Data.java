package com.sn.saba_source.dto;

import com.sn.saba_source.model.mongo.RawBetDetail;

import java.util.List;

@lombok.Data
public class Data {

    private Long last_version_key;
    private List<RawBetDetail> BetDetails;
    private List<RawBetDetail> BetNumberDetails;
    private List<RawBetDetail> BetVirtualSportDetails;

}
