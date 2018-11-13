package com.sn.springcloudkafkaproducer.exception.exception;/**
 * Created by pc-001 on 8/14/18.
 */

/**
 * Created by Kier pc-001 on 8/14/18.
 */

public class ValidationException extends Exception {

    private String error;
    private String key;

    public ValidationException(String key, String error) {
        super("key: " + key + " error: " + error);
        this.key = key;
        this.error = error;
    }

    public String getKey() {
        return key;
    }

    public String getError() {
        return error;
    }

}
