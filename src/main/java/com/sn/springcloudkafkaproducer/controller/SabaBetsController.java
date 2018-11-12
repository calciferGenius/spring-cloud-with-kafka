package com.sn.springcloudkafkaproducer.controller;

import com.sn.springcloudkafkaproducer.model.SabaBetData;
import com.sn.springcloudkafkaproducer.repository.mongo.BetDataRepository;
import com.sn.springcloudkafkaproducer.service.SabaService;
import com.sn.springcloudkafkaproducer.utils.JodaDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SabaBetsController {

    @Autowired
    private SabaService sabaService;

    @Autowired
    private BetDataRepository betDataRepository;

    @GetMapping("/bet")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void greetings(@RequestParam("betNo") int betNo) {
        int maxBetNo = betNo + 20;
        for (int i = betNo; i < maxBetNo ; i++) {
            SabaBetData sabaBetData = SabaBetData.builder()
                    .betNo("SABA-"+i)
                    .betAmount(10d)
                    .commissionable(10d)
                    .payOff(5d)
                    .loginname("claud")
                    .wagersDate(JodaDateUtils.convertDateToTimeStamp(new Date()))
                    .build();
            betDataRepository.save(sabaBetData);
            sabaService.sendBetData(sabaBetData);
        }

    }
}
