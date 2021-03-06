package com.sn.springcloudkafkaproducer.exception.http;/**
 * Created by pc-001 on 9/4/18.
 */


import com.sn.springcloudkafkaproducer.exception.exception.HttpComponentException;

/**
 * Created by Kier pc-001 on 9/4/18.
 */

public class TraceFailedException extends HttpComponentException {

    public TraceFailedException() {
        super("Trace failed,please check the network connection");
    }

    public TraceFailedException(String error) {
        super(error);
    }
}