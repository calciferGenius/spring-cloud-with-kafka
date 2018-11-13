package com.sn.saba_source.exception.http;/**
 * Created by pc-001 on 9/4/18.
 */


import com.sn.saba_source.exception.exception.HttpComponentException;

/**
 * Created by Kier pc-001 on 9/4/18.
 */

public class DeleteFailedException extends HttpComponentException {

    public DeleteFailedException() {
        super("Delete failed,please check the network connection");
    }

    public DeleteFailedException(String error) {
        super(error);
    }
}