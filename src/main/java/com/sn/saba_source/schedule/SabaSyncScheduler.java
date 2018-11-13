package com.sn.saba_source.schedule;

import com.google.gson.Gson;
import com.sn.saba_source.dto.ApiBetData;
import com.sn.saba_source.dto.Data;
import com.sn.saba_source.model.mongo.RawBetDetail;
import com.sn.saba_source.model.mysql.SyncBetOrderConfig;
import com.sn.saba_source.repository.mongo.RawBetDetailRepository;
import com.sn.saba_source.repository.mysql.SyncBetOrderConfigRepository;
import com.sn.saba_source.service.SabaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class SabaSyncScheduler {

    @Autowired
    private SyncBetOrderConfigRepository syncBetOrderConfigRepository;

    @Autowired
    private RawBetDetailRepository rawBetDetailRepository;

    @Autowired
    private SabaService sabaService;

    @Scheduled(cron = "0 0/2 * 1/1 * ?")
    public void forwardSync(){
        SyncBetOrderConfig syncBetOrderConfig = syncBetOrderConfigRepository.findSyncBetOrderConfigByProviderId(86);
        if(syncBetOrderConfig != null){
            log.info("sync config "+syncBetOrderConfig.toString());
        } else {
            log.info("------------------------ sync config is empty --------------------------");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("vendor_id", syncBetOrderConfig.getAgent());
        params.put("version_key", Integer.parseInt(syncBetOrderConfig.getQueryString()));

        try {
            String response = "{\"error_code\":0,\"message\":\"\",\"Data\":{\"last_version_key\":68346,\"BetDetails\":[{\"trans_id\":104887929422,\"vendor_member_id\":\"bwadevtest\",\"operator_id\":\"Callmeboss\",\"league_id\":77640,\"match_id\":26793099,\"home_id\":370586,\"away_id\":323906,\"match_datetime\":\"2018-09-13T06:11:59\",\"sport_type\":9,\"bet_type\":1,\"parlay_ref_no\":0,\"odds\":3.08,\"stake\":10,\"transaction_time\":\"2018-09-13T06:47:42.703\",\"ticket_status\":\"LOSE\",\"winlost_amount\":-10,\"after_amount\":270,\"currency\":13,\"winlost_datetime\":\"2018-09-13T00:00:00\",\"odds_type\":3,\"bet_team\":\"a\",\"isLucky\":\"False\",\"home_hdp\":1.5,\"away_hdp\":0,\"hdp\":1.5,\"betfrom\":\"x\",\"islive\":\"1\",\"home_score\":0,\"away_score\":0,\"customInfo1\":\"\",\"customInfo2\":\"\",\"customInfo3\":\"\",\"customInfo4\":\"\",\"customInfo5\":\"\",\"ba_status\":\"0\",\"version_key\":34086,\"ParlayData\":null},{\"trans_id\":104919970701,\"vendor_member_id\":\"xbtdevcarl\",\"operator_id\":\"Callmeboss\",\"league_id\":null,\"match_id\":29,\"match_datetime\":\"2018-09-16T00:00:00\",\"sport_type\":null,\"bet_type\":29,\"parlay_ref_no\":101200462654,\"odds\":0,\"stake\":40,\"transaction_time\":\"2018-09-16T21:22:45.6\",\"ticket_status\":\"Reject\",\"winlost_amount\":0,\"after_amount\":38,\"currency\":13,\"winlost_datetime\":\"2018-09-16T00:00:00\",\"odds_type\":3,\"bet_team\":\"3\",\"isLucky\":\"False\",\"parlay_type\":\"System Parlay\",\"combo_type\":\"Doubles (3 Bets),Treble (1 Bet)\",\"home_hdp\":1,\"away_hdp\":3,\"hdp\":null,\"betfrom\":\"x\",\"islive\":\"0\",\"home_score\":null,\"away_score\":null,\"customInfo1\":\"\",\"customInfo2\":\"\",\"customInfo3\":\"\",\"customInfo4\":\"\",\"customInfo5\":\"\",\"ba_status\":\"0\",\"version_key\":34586,\"ParlayData\":[{\"parlay_id\":2145811487,\"league_id\":11466,\"match_id\":26805849,\"home_id\":9055,\"away_id\":9075,\"match_datetime\":\"2018-09-16T00:00:00\",\"odds\":5.2,\"bet_type\":5,\"bet_team\":\"x\",\"sport_type\":1,\"home_hdp\":1.1,\"away_hdp\":1,\"hdp\":null,\"islive\":\"1\",\"home_score\":1,\"away_score\":0,\"ticket_status\":\"Reject\",\"winlost_datetime\":\"2018-09-16T00:00:00\"},{\"parlay_id\":2145811488,\"league_id\":5625,\"match_id\":26831744,\"home_id\":14409,\"away_id\":289600,\"match_datetime\":\"2018-09-16T00:00:00\",\"odds\":1.47,\"bet_type\":20,\"bet_team\":\"a\",\"sport_type\":5,\"home_hdp\":0,\"away_hdp\":0,\"hdp\":null,\"islive\":\"0\",\"home_score\":null,\"away_score\":null,\"ticket_status\":\"WON\",\"winlost_datetime\":\"2018-09-17T00:00:00\"},{\"parlay_id\":2145811489,\"league_id\":14322,\"match_id\":26845281,\"home_id\":259519,\"away_id\":427941,\"match_datetime\":\"2018-09-16T00:00:00\",\"odds\":2.2,\"bet_type\":20,\"bet_team\":\"h\",\"sport_type\":5,\"home_hdp\":0,\"away_hdp\":0,\"hdp\":null,\"islive\":\"0\",\"home_score\":null,\"away_score\":null,\"ticket_status\":\"WON\",\"winlost_datetime\":\"2018-09-17T00:00:00\"}]}],\"BetNumberDetails\":[{\"trans_id\":104933574560,\"vendor_member_id\":\"kblksabatest03\",\"operator_id\":\"Callmeboss\",\"match_id\":26885901,\"transaction_time\":\"2018-09-19T05:17:30.157\",\"odds\":1.98,\"stake\":2,\"ticket_status\":\"WON\",\"betfrom\":\"x\",\"islive\":\"1\",\"last_ball_no\":35,\"bet_team\":\"a\",\"winlost_datetime\":\"2018-09-19T00:00:00\",\"bet_type\":85,\"currency\":13,\"odds_type\":3,\"sport_type\":161,\"winlost_amount\":1.96,\"after_amount\":188,\"customInfo1\":\"\",\"customInfo2\":\"\",\"customInfo3\":\"\",\"customInfo4\":\"\",\"customInfo5\":\"\",\"ba_status\":\"0\",\"version_key\":34934},{\"trans_id\":104950226052,\"vendor_member_id\":\"xbtdevcarl\",\"operator_id\":\"Callmeboss\",\"match_id\":26915051,\"transaction_time\":\"2018-09-21T06:17:49.083\",\"odds\":71.42,\"stake\":1,\"ticket_status\":\"LOSE\",\"betfrom\":\"x\",\"islive\":\"1\",\"last_ball_no\":0,\"bet_team\":\"5-5\",\"winlost_datetime\":\"2018-09-21T00:00:00\",\"bet_type\":90,\"currency\":13,\"odds_type\":3,\"sport_type\":161,\"winlost_amount\":-1,\"after_amount\":80.435,\"customInfo1\":\"\",\"customInfo2\":\"\",\"customInfo3\":\"\",\"customInfo4\":\"\",\"customInfo5\":\"\",\"ba_status\":\"0\",\"version_key\":67444}],\"BetVirtualSportDetails\":[{\"trans_id\":104920123256,\"vendor_member_id\":\"kblksabatest\",\"operator_id\":\"Callmeboss\",\"league_id\":26061,\"match_id\":26843199,\"home_id\":178282,\"away_id\":178294,\"match_datetime\":\"2018-09-16T22:21:00\",\"sport_type\":180,\"bet_type\":1201,\"parlay_ref_no\":0,\"odds\":2.06,\"stake\":10,\"transaction_time\":\"2018-09-16T22:19:10.513\",\"ticket_status\":\"LOSE\",\"winlost_amount\":-10,\"after_amount\":70,\"currency\":13,\"winlost_datetime\":\"2018-09-16T00:00:00\",\"odds_type\":3,\"bet_team\":\"a\",\"home_hdp\":0.25,\"away_hdp\":0,\"hdp\":0.25,\"betfrom\":\"a\",\"customInfo1\":\"\",\"customInfo2\":\"\",\"customInfo3\":\"\",\"customInfo4\":\"\",\"customInfo5\":\"\",\"ba_status\":\"0\",\"version_key\":34189,\"ParlayData\":null},{\"trans_id\":104940353534,\"vendor_member_id\":\"xbtdevcarl\",\"operator_id\":\"Callmeboss\",\"league_id\":null,\"match_id\":2799,\"match_datetime\":\"2018-09-20T00:00:00\",\"sport_type\":null,\"bet_type\":2799,\"parlay_ref_no\":101202448044,\"odds\":7.5338,\"stake\":1,\"transaction_time\":\"2018-09-20T05:05:41.493\",\"ticket_status\":\"LOSE\",\"winlost_amount\":-1,\"after_amount\":60.7,\"currency\":13,\"winlost_datetime\":\"2018-09-20T00:00:00\",\"odds_type\":3,\"bet_team\":\"1\",\"home_hdp\":1,\"away_hdp\":3,\"hdp\":null,\"betfrom\":\"x\",\"customInfo1\":\"\",\"customInfo2\":\"\",\"customInfo3\":\"\",\"customInfo4\":\"\",\"customInfo5\":\"\",\"ba_status\":\"0\",\"version_key\":66023,\"ParlayData\":[{\"parlay_id\":2149248831,\"league_id\":66626,\"match_id\":26900667,\"home_id\":414852,\"away_id\":414867,\"match_datetime\":\"2018-09-20T00:00:00\",\"odds\":1.75,\"bet_type\":2705,\"bet_team\":\"h\",\"sport_type\":190,\"home_hdp\":1.25,\"away_hdp\":0,\"hdp\":-1.25,\"islive\":\"0\",\"home_score\":null,\"away_score\":null,\"ticket_status\":\"WON\",\"winlost_datetime\":\"2018-09-20T00:00:00\"},{\"parlay_id\":2149248832,\"league_id\":66626,\"match_id\":26900666,\"home_id\":414857,\"away_id\":414855,\"match_datetime\":\"2018-09-20T00:00:00\",\"odds\":2.05,\"bet_type\":2703,\"bet_team\":\"o\",\"sport_type\":190,\"home_hdp\":3,\"away_hdp\":0,\"hdp\":3,\"islive\":\"0\",\"home_score\":null,\"away_score\":null,\"ticket_status\":\"LOSE\",\"winlost_datetime\":\"2018-09-20T00:00:00\"},{\"parlay_id\":2149248833,\"league_id\":66626,\"match_id\":26900668,\"home_id\":414851,\"away_id\":414859,\"match_datetime\":\"2018-09-20T00:00:00\",\"odds\":2.1,\"bet_type\":2705,\"bet_team\":\"a\",\"sport_type\":190,\"home_hdp\":1.5,\"away_hdp\":0,\"hdp\":1.5,\"islive\":\"0\",\"home_score\":null,\"away_score\":null,\"ticket_status\":\"LOSE\",\"winlost_datetime\":\"2018-09-20T00:00:00\"}]}]}}";

                    //sabaService.processHttpSuccessResponse(RequestMethod.POST, syncBetOrderConfig.getCallUrl(), params, Constants.HTTP_TIMEOUT);
            log.info("===============================================");
            log.info(response);

            Gson gson = new Gson();
            ApiBetData apiBetData = gson.fromJson(response, ApiBetData.class);

            if(apiBetData.getData() != null){
                Data data = apiBetData.getData();
                if(data != null){
                    List<RawBetDetail> betDetails = data.getBetDetails();
                    if(betDetails != null){
                        log.info("betDetails ===> "+betDetails);
                        persistsRawBetDetails(betDetails);
                        //rawBetDetailRepository.saveAll(betDetails);
                    }

                    List<RawBetDetail> betNumberDetails = data.getBetNumberDetails();
                    if(betNumberDetails != null){
                        log.info("betNumberDetails ===> "+betNumberDetails);
                        //rawBetDetailRepository.saveAll(betNumberDetails);
                    }

                    List<RawBetDetail> betVirtualSportDetails = data.getBetVirtualSportDetails();
                    if(betVirtualSportDetails != null){
                        log.info("betVirtualSportDetails ===> "+betVirtualSportDetails);
                        //rawBetDetailRepository.saveAll(betVirtualSportDetails);
                    }

                    if(data.getBetDetails() == null && data.getBetNumberDetails() == null && data.getBetVirtualSportDetails() == null){
                        log.info("Bet details are null... " + response);
                    }

                    if(data.getLast_version_key() != null){
                        syncBetOrderConfig.setQueryString(data.getLast_version_key().toString());
                        syncBetOrderConfigRepository.save(syncBetOrderConfig);
                    }

                }
            }

            log.info("===============================================");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Exception: "+e.getMessage());
        }
//        catch (HttpComponentException e) {
//            e.printStackTrace();
//            log.info("HttpComponentException: "+e.getMessage());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            log.info("UnsupportedEncodingException: "+e.getMessage());
//        } catch (ResponseFailedException e) {
//            e.printStackTrace();
//            log.info("ResponseFailedException: "+e.getMessage());
//        }
    }

    public void persistsRawBetDetails(List<RawBetDetail> rawBets){
        rawBets.stream().forEach(rawBetDetail ->
                {
                    if(rawBetDetail.getTrans_id() != null){
                        RawBetDetail rawBet = rawBetDetailRepository.getRawBetDetailByTransId(rawBetDetail.getTrans_id());
                        if(rawBet == null){
                            log.info("new bets "+rawBet);
                            //rawBetDetailRepository.save(rawBetDetail);
                        } else {
                            log.info("raw bet ---> "+rawBet.toString());
                            rawBetDetailRepository.updateBetDetail(rawBet);
                            //rawBetDetailRepository.save(updateRawBetDetails);
                        }
                    } else {
                        log.info("trans_id is null");
                    }

                }
        );
    }
}