package com.sn.saba_source.exception.http;/**
 * Created by pc-001 on 9/4/18.
 */


import com.sn.saba_source.exception.exception.HttpComponentException;

/**
 * Created by Kier pc-001 on 9/4/18.
 */

public class OptionsFailedException extends HttpComponentException {

    public OptionsFailedException() {
        super("Options failed,please check the network connection");
    }

    public OptionsFailedException(String error) {
        super(error);
    }
}