package com.sn.springcloudkafkaproducer.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class RawBetDetails {

    //betdetails
    private Long trans_id;
    private String vendor_member_id;
    private String operator_id;
    private Integer league_id;
    private Integer match_id;
    private Integer home_id;
    private Integer away_id;
    private Integer team_id;
    private String match_datetime;
    private Integer sport_type;
    private Integer bet_type;
    private Long parlay_ref_no;
    private Double odds;
    private Double stake;
    private String transaction_time;
    private String ticket_status;
    private Double winlost_amount;
    private Double after_amount;
    private Integer currency;
    private String winlost_datetime;
    private Integer odds_type;
    private String isLucky;
    private String bet_team;
    private String exculding;
    private String bet_tag;
    private Double home_hdp;
    private Double away_hdp;
    private Double hdp;
    private String betfrom;
    private String islive;
    private Integer home_score;
    private Integer away_score;
    private String customInfo1;
    private String customInfo2;
    private String customInfo3;
    private String customInfo4;
    private String customInfo5;
    private String ba_status;
    private Integer version_key;

    private String parlay_type;
    private String combo_type;
    private Long Parlay_id;
    private String pool_type;
    private String lottery_bettype;
    private Double validbetamount;
    private Double original_stake;
    private String race_number;
    private String race_lane;
    private Integer range;

    //bet number details
    private Integer last_ball_no;

    //Parlay data
    private List<ParlayData> ParlayData;
    private List<CashoutData> CashOutData;

}
