package com.sn.springcloudkafkaproducer.schedule;

import com.sn.springcloudkafkaproducer.model.SyncBetOrderConfig;
import com.sn.springcloudkafkaproducer.repository.mysql.SyncBetOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SabaSyncScheduler {

    @Autowired
    private SyncBetOrderRepository syncBetOrderRepository;

    @Scheduled(fixedDelay = 10000)
    public void forwardSync(){
        SyncBetOrderConfig syncBetOrderConfig = syncBetOrderRepository.findSyncBetOrderConfigByProviderIdAndPlatformId(86, 13);
        if(syncBetOrderConfig != null){
            log.info("sync config "+syncBetOrderConfig.toString());
        } else {
            log.info("------------------------ sync config is empty --------------------------");
        }
    }


}
