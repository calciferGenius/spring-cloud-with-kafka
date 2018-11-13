package com.sn.saba_source.repository.mongo;

import com.sn.saba_source.model.mongo.RawBetDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RawBetDetailRepositoryImpl implements RawBetDetailRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public RawBetDetail getRawBetDetailByTransId(Long transId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("trans_id").is(transId));
        return mongoTemplate.findOne(query, RawBetDetail.class);
    }

    @Override
    public void updateBetDetail(RawBetDetail rawBetDetail) {

        Query query = new Query();
        query.addCriteria(Criteria.where("trans_id").is(rawBetDetail.getTrans_id()));

        Update update = new Update();
        update.set("ticket_status", rawBetDetail.getTicket_status());
        update.set("stake", rawBetDetail.getStake());
        update.set("winlost_amount", rawBetDetail.getWinlost_amount());
        mongoTemplate.upsert(query, update, RawBetDetail.class);
    }

    @Override
    public <S extends RawBetDetail> S save(S s) {
        return null;
    }

    @Override
    public <S extends RawBetDetail> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<RawBetDetail> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<RawBetDetail> findAll() {
        return null;
    }

    @Override
    public Iterable<RawBetDetail> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(RawBetDetail rawBetDetail) {

    }

    @Override
    public void deleteAll(Iterable<? extends RawBetDetail> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<RawBetDetail> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<RawBetDetail> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends RawBetDetail> S insert(S s) {
        return null;
    }

    @Override
    public <S extends RawBetDetail> List<S> insert(Iterable<S> iterable) {
        return null;
    }

    @Override
    public <S extends RawBetDetail> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends RawBetDetail> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends RawBetDetail> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends RawBetDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends RawBetDetail> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends RawBetDetail> boolean exists(Example<S> example) {
        return false;
    }
}
