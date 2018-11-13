package com.sn.springcloudkafkaproducer.exception.http;


import com.sn.springcloudkafkaproducer.exception.exception.HttpComponentException;

/**
 * 
 * @author Win
 * 
 * @version 2011-4-9 下午04:37:10
 * 
 */
public class PostFailedException extends HttpComponentException {

	public PostFailedException() {
		super("Post failed,please check the network connection");
	}

	public PostFailedException(String error) {
		super(error);
	}
}
