package com.sn.springcloudkafkaproducer.repository.mongo;

import com.sn.springcloudkafkaproducer.dto.RawBetDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RawBetHistoryRepository extends MongoRepository<RawBetDetails, Long> {

    public RawBetDetails findByTrans_id(Long trans_id);

}
