package com.sn.saba_source.model.mongo;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.List;

@Data
@Document(collection="saba_raw_bet_detail")
public class RawBetDetail {

    @Indexed(unique = true)
    @Field private Long trans_id;
    @Field private String vendor_member_id;
    @Field private String operator_id;
    @Field private Integer league_id;
    @Field private Integer match_id;
    @Field private Integer home_id;
    @Field private Integer away_id;
    @Field private Integer team_id;
    @Field private String match_datetime;
    @Field private Integer sport_type;
    @Field private Integer bet_type;
    @Field private Long parlay_ref_no;
    @Field private Double odds;
    @Field private Double stake;
    @Field private String transaction_time;
    @Field private String ticket_status;
    @Field private Double winlost_amount;
    @Field private Double after_amount;
    @Field private Integer currency;
    @Field private String winlost_datetime;
    @Field private Integer odds_type;
    @Field private String isLucky;
    @Field private String bet_team;
    @Field private String exculding;
    @Field private String bet_tag;
    @Field private Double home_hdp;
    @Field private Double away_hdp;
    @Field private Double hdp;
    @Field private String betfrom;
    @Field private String islive;
    @Field private Integer home_score;
    @Field private Integer away_score;
    @Field private String customInfo1;
    @Field private String customInfo2;
    @Field private String customInfo3;
    @Field private String customInfo4;
    @Field private String customInfo5;
    @Field private String ba_status;
    @Field private Integer version_key;

    @Field private String parlay_type;
    @Field private String combo_type;
    @Field private Long Parlay_id;
    @Field private String pool_type;
    @Field private String lottery_bettype;
    @Field private Double validbetamount;
    @Field private Double original_stake;
    @Field private String race_number;
    @Field private String race_lane;
    @Field private Integer range;

    //bet number details
    @Field private Integer last_ball_no;

    //Parlay data
    @Field private List<ApiParlayData> ParlayData;
    @Field private List<ApiCashoutData> CashOutData;

}
