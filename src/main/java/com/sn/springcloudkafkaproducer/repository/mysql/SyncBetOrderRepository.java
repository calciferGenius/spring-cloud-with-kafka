package com.sn.springcloudkafkaproducer.repository.mysql;

import com.sn.springcloudkafkaproducer.model.SyncBetOrderConfig;
import org.springframework.data.repository.Repository;

public interface SyncBetOrderRepository extends Repository<SyncBetOrderConfig, Long> {

    public SyncBetOrderConfig findSyncBetOrderConfigByProviderIdAndPlatformId(Integer providerId, Integer platformId);
}
