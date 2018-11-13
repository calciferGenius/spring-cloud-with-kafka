package com.sn.saba_source.repository.mysql;

import com.sn.saba_source.model.mysql.SyncBetOrderConfig;
import org.springframework.data.repository.CrudRepository;

public interface SyncBetOrderConfigRepository extends CrudRepository<SyncBetOrderConfig, Long> {

    public SyncBetOrderConfig findSyncBetOrderConfigByProviderIdAndPlatformId(Integer providerId, Integer platformId);

    public SyncBetOrderConfig findSyncBetOrderConfigByProviderId(Integer providerId);
}
