package com.sn.saba_source.repository.mongo;


import com.sn.saba_source.model.mongo.RawBetDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RawBetDetailRepository extends MongoRepository<RawBetDetail, Long> {

    public RawBetDetail getRawBetDetailByTransId(Long transId);

    public void updateBetDetail(RawBetDetail rawBetDetail);
}
