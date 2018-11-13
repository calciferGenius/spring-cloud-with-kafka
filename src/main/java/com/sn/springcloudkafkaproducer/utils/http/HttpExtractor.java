package com.sn.springcloudkafkaproducer.utils.http;/**
 * Created by pc-001 on 9/4/18.
 */

import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;

/**
 * Created by Kier pc-001 on 9/4/18.
 */

public class HttpExtractor {

    private final static Log log = LogFactory.getLog(HttpComponent.class);

    public static HttpResponse extractHttp(HttpMethodBase httpMethodBase){
        String responseBody = "";
        try {
            responseBody = httpMethodBase.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception occurred while getting response body. Will return blank response");

        }
        return new HttpResponse(httpMethodBase.getName(), httpMethodBase.getStatusCode(), responseBody);
    }

}
