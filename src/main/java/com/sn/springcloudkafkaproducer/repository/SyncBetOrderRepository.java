package com.sn.springcloudkafkaproducer.repository;

import com.sn.springcloudkafkaproducer.model.SyncBetOrderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SyncBetOrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SyncBetOrderConfig getSyncConfigByProviderIdAndPlatformId(Integer providerId, Integer platformId){
        SyncBetOrderConfig syncBetOrderConfig = null;

        return syncBetOrderConfig;

    }
}
