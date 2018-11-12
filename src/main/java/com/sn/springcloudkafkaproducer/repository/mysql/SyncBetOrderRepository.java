package com.sn.springcloudkafkaproducer.repository.mysql;

import com.sn.springcloudkafkaproducer.model.SyncBetOrderConfig;
import org.springframework.data.repository.CrudRepository;

public interface SyncBetOrderRepository extends CrudRepository<SyncBetOrderConfig, Long> {

    public SyncBetOrderConfig findSyncBetOrderConfigByProviderIdAndPlatformId(Integer providerId, Integer platformId);
}
