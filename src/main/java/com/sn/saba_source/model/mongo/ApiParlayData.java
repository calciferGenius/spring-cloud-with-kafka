package com.sn.saba_source.model.mongo;

import lombok.Data;

@Data
public class ApiParlayData {

    private Long parlay_id;
    private Integer league_id;
    private Integer match_id;
    private Integer home_id;
    private Integer away_id;
    private String match_datetime;
    private Double odds;
    private Integer bet_type;
    private String bet_team;
    private Integer sport_type;
    private Double home_dp;
    private Double away_dp;
    private Double hdp;
    private String islive;
    private Integer home_score;
    private Integer away_score;
    private String ticket_status;
    private String winlost_datetime;
}
