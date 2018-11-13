package com.sn.springcloudkafkaproducer.exception.http;/**
 * Created by pc-001 on 7/3/18.
 */

import com.sn.springcloudkafkaproducer.exception.exception.HttpComponentException;

/**
 * Created by Kier pc-001 on 7/3/18.
 */

public class GetFailedException extends HttpComponentException {

    public GetFailedException() {
        super("Get failed,please check the network connection");
    }

    public GetFailedException(String error) {
        super(error);
    }
}
