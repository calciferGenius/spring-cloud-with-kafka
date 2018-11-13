package com.sn.springcloudkafkaproducer.exception.exception;/**
 * Created by pc-001 on 7/3/18.
 */

/**
 * Created by Kier pc-001 on 7/3/18.
 */

public class HttpComponentException extends GenericException {

    public HttpComponentException() {
        super("HttpComponent have problem, check parameters");
    }

    public HttpComponentException(String error) {
        super(error);
    }
}
