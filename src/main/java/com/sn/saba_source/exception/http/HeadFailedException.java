package com.sn.saba_source.exception.http;

import com.sn.saba_source.exception.exception.HttpComponentException;

public class HeadFailedException extends HttpComponentException {

    public HeadFailedException() {
        super("Head failed,please check the network connection");
    }

    public HeadFailedException(String error) {
        super(error);
    }
}
