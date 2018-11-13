package com.sn.springcloudkafkaproducer.schedule;

import com.google.gson.Gson;
import com.sn.springcloudkafkaproducer.constants.Constants;
import com.sn.springcloudkafkaproducer.dto.ApiBetData;
import com.sn.springcloudkafkaproducer.dto.Data;
import com.sn.springcloudkafkaproducer.dto.RawBetDetails;
import com.sn.springcloudkafkaproducer.exception.exception.HttpComponentException;
import com.sn.springcloudkafkaproducer.exception.exception.ResponseFailedException;
import com.sn.springcloudkafkaproducer.model.SyncBetOrderConfig;
import com.sn.springcloudkafkaproducer.repository.mongo.RawBetHistoryRepository;
import com.sn.springcloudkafkaproducer.repository.mysql.SyncBetOrderRepository;
import com.sn.springcloudkafkaproducer.service.SabaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class SabaSyncScheduler {

    @Autowired
    private SyncBetOrderRepository syncBetOrderRepository;

    @Autowired
    private RawBetHistoryRepository rawBetHistoryRepository;

    @Autowired
    private SabaService sabaService;

    @Scheduled(cron = "0 0/2 * 1/1 * ?")
    public void forwardSync(){
        SyncBetOrderConfig syncBetOrderConfig = syncBetOrderRepository.findSyncBetOrderConfigByProviderId(86);
        if(syncBetOrderConfig != null){
            log.info("sync config "+syncBetOrderConfig.toString());
        } else {
            log.info("------------------------ sync config is empty --------------------------");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("vendor_id", syncBetOrderConfig.getAgent());
        params.put("version_key", Integer.parseInt(syncBetOrderConfig.getQueryString()));

        try {
            String response =  sabaService.processHttpSuccessResponse(RequestMethod.POST, syncBetOrderConfig.getCallUrl(), params, Constants.HTTP_TIMEOUT);
            log.info("===============================================");
            log.info(response);

            Gson gson = new Gson();
            ApiBetData apiBetData = gson.fromJson(response, ApiBetData.class);

            if(apiBetData.getData() != null){
                Data data = apiBetData.getData();
                if(data != null){
                    List<RawBetDetails> betDetails = data.getBetDetails();
                    if(betDetails != null){
                        //do something here
                        log.info("betDetails ===> "+betDetails);
                        rawBetHistoryRepository.saveAll(betDetails);
                    }

                    List<RawBetDetails> betNumberDetails = data.getBetNumberDetails();
                    if(betNumberDetails != null){
                        //do something here
                        log.info("betNumberDetails ===> "+betNumberDetails);
                        rawBetHistoryRepository.saveAll(betNumberDetails);
                    }

                    List<RawBetDetails> betVirtualSportDetails = data.getBetVirtualSportDetails();
                    if(betVirtualSportDetails != null){
                        //do something here
                        log.info("betVirtualSportDetails ===> "+betVirtualSportDetails);
                        rawBetHistoryRepository.saveAll(betVirtualSportDetails);
                    }

                    if(data.getBetDetails() == null && data.getBetNumberDetails() == null && data.getBetVirtualSportDetails() == null){
                        log.info("Bet details are null... " + response);
                    }

                    if(data.getLast_version_key() != null){
                        syncBetOrderConfig.setQueryString(data.getLast_version_key().toString());
                        syncBetOrderRepository.save(syncBetOrderConfig);
                    }

                }
            }

            log.info("===============================================");
        } catch (HttpComponentException e) {
            e.printStackTrace();
            log.info("HttpComponentException: "+e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.info("UnsupportedEncodingException: "+e.getMessage());
        } catch (ResponseFailedException e) {
            e.printStackTrace();
            log.info("ResponseFailedException: "+e.getMessage());
        }
    }

    public void persistsRawBetDetails(List<RawBetDetails> rawBets){
        rawBets.stream().forEach(rawBetDetails ->
                {
                    RawBetDetails rawBet = rawBetHistoryRepository.findByTrans_id(rawBetDetails.getTrans_id());
                    if(rawBet == null){
                        rawBetHistoryRepository.save(rawBetDetails);
                    } else {
                        RawBetDetails updateRawBetDetails = rawBet;
                        rawBetHistoryRepository.save(updateRawBetDetails);
                    }
                }
        );
    }
}
