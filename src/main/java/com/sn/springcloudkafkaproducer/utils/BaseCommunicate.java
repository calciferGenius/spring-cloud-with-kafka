package com.sn.springcloudkafkaproducer.utils;

import com.sn.springcloudkafkaproducer.constants.Constants;
import com.sn.springcloudkafkaproducer.enums.HttpHeader;
import com.sn.springcloudkafkaproducer.exception.exception.HttpComponentException;
import com.sn.springcloudkafkaproducer.exception.exception.ResponseFailedException;
import com.sn.springcloudkafkaproducer.exception.http.*;
import com.sn.springcloudkafkaproducer.utils.http.HttpComponent;
import com.sn.springcloudkafkaproducer.utils.http.HttpExtractor;
import com.sn.springcloudkafkaproducer.utils.http.HttpResponse;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class BaseCommunicate {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseCommunicate.class);


    public HttpResponse processHttp(RequestMethod requestMethod, String url, Map<String, Object> params, Integer timeout) throws PostFailedException, ResponseFailedException, GetFailedException, HttpComponentException, DeleteFailedException, OptionsFailedException, HeadFailedException, UnsupportedEncodingException, PutFailedException, TraceFailedException {
        return HttpExtractor.extractHttp(HttpComponent.sendAPIRequest(requestMethod, url, constructParams(params), null, timeout));
    }

    public String processHttpSuccessResponse(RequestMethod requestMethod, String url, Map<String, Object> params, Integer timeout) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getSuccessResponse(processHttp(requestMethod, url, params, timeout)) ;
    }

    public String processHttpResponse(RequestMethod requestMethod, String url, Map<String, Object> params, Integer timeout, int code) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponse(processHttp(requestMethod, url, params, timeout), code);
    }

    public String processHttpResponseValidRange(RequestMethod requestMethod, String url, Map<String, Object> params, Integer timeout, int[] validRange) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponseValidRange(processHttp(requestMethod, url, params, timeout), validRange);
    }

    public String processHttpResponseMatchCode(RequestMethod requestMethod, String url, Map<String, Object> params, Integer timeout, int[] statusCodes) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponseMatchCode(processHttp(requestMethod, url, params, timeout), statusCodes);
    }




    //WITH HEADER REQUESTS


    public HttpResponse processHttp(RequestMethod requestMethod, String url, Map<String, Object> params, Map<String, String> headers, Integer timeout) throws PostFailedException, ResponseFailedException, GetFailedException, HttpComponentException, DeleteFailedException, OptionsFailedException, HeadFailedException, UnsupportedEncodingException, PutFailedException, TraceFailedException {
        return HttpExtractor.extractHttp(HttpComponent.sendAPIRequest(requestMethod, url, constructParams(params), headers, timeout));
    }

    public String processHttpSuccessResponse(RequestMethod requestMethod, String url, Map<String, Object> params, Map<String, String> headers, Integer timeout) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getSuccessResponse(processHttp(requestMethod, url, params, headers, timeout)) ;
    }

    public String processHttpResponse(RequestMethod requestMethod, String url, Map<String, Object> params, Map<String, String> headers, Integer timeout, int code) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponse(processHttp(requestMethod, url, params, headers, timeout), code);
    }

    public String processHttpResponseValidRange(RequestMethod requestMethod, String url, Map<String, Object> params, Map<String, String> headers, Integer timeout, int[] validRange) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponseValidRange(processHttp(requestMethod, url, params, headers, timeout), validRange);
    }

    public String processHttpResponseMatchCode(RequestMethod requestMethod, String url, Map<String, Object> params, Map<String, String> headers, Integer timeout, int[] statusCodes) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponseMatchCode(processHttp(requestMethod, url, params, headers, timeout), statusCodes);
    }


    // RAW REQUEST
    /**
     *
     * @param requestMethod
     * @param url
     * @param contentType
     * @param encoding
     * @param content
     * @param timeout
     * @return
     * @throws HttpComponentException
     * @throws ResponseFailedException
     * @throws PostFailedException
     * @throws UnsupportedEncodingException
     */
    public HttpResponse processHttp(RequestMethod requestMethod, String url, String contentType, String encoding, String content, Integer timeout) throws HttpComponentException, ResponseFailedException, PostFailedException, UnsupportedEncodingException, PutFailedException {
        return HttpExtractor.extractHttp(HttpComponent.sendAPIRequest(requestMethod, url, contentType, encoding, content, null, timeout));
    }

    public String processHttpSuccessResponse(RequestMethod requestMethod, String url, String contentType, String encoding, String content, Integer timeout) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getSuccessResponse(processHttp(requestMethod, url, contentType, encoding, content, timeout)) ;
    }

    public String processHttpResponse(RequestMethod requestMethod, String url, String contentType, String encoding, String content, Integer timeout, int code) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponse(processHttp(requestMethod, url, contentType, encoding, content, timeout), code);
    }

    public String processHttpResponseValidRange(RequestMethod requestMethod, String url, String contentType, String encoding, String content, Integer timeout, int[] validRange) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponseValidRange(processHttp(requestMethod, url, contentType, encoding, content, timeout), validRange);
    }

    public String processHttpResponseMatchCode(RequestMethod requestMethod, String url, String contentType, String encoding, String content, Integer timeout, int[] statusCodes) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponseMatchCode(processHttp(requestMethod, url, contentType, encoding, content, timeout), statusCodes);
    }



    // RAW WITH HEADERS
    /**
     * @param requestMethod
     * @param url
     * @param contentType
     * @param encoding
     * @param content
     * @param headers
     * @param timeout
     * @return
     * @throws HttpComponentException
     * @throws ResponseFailedException
     * @throws PostFailedException
     * @throws UnsupportedEncodingException
     */
    public HttpResponse processHttp(RequestMethod requestMethod, String url, String contentType, String encoding, String content, Map<String, String> headers, Integer timeout) throws HttpComponentException, ResponseFailedException, PostFailedException, UnsupportedEncodingException, PutFailedException {
        return HttpExtractor.extractHttp(HttpComponent.sendAPIRequest(requestMethod, url, contentType, encoding, content, headers, timeout));
    }

    public String processHttpSuccessResponse(RequestMethod requestMethod, String url, String contentType, String encoding, String content, Map<String, String> headers, Integer timeout) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getSuccessResponse(processHttp(requestMethod, url, contentType, encoding, content, headers, timeout)) ;
    }

    public String processHttpResponse(RequestMethod requestMethod, String url, String contentType, String encoding, String content, Map<String, String> headers, Integer timeout, int code) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponse(processHttp(requestMethod, url, contentType, encoding, content, headers, timeout), code);
    }

    public String processHttpResponseValidRange(RequestMethod requestMethod, String url, String contentType, String encoding, String content, Map<String, String> headers, Integer timeout, int[] validRange) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponseValidRange(processHttp(requestMethod, url, contentType, encoding, content, headers, timeout), validRange);
    }

    public String processHttpResponseMatchCode(RequestMethod requestMethod, String url, String contentType, String encoding, String content, Map<String, String> headers, Integer timeout, int[] statusCodes) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponseMatchCode(processHttp(requestMethod, url, contentType, encoding, content, headers, timeout), statusCodes);
    }

    /**
     * Pre-defined App JSON raw request
     * @param requestMethod
     * @param url
     * @param content
     * @param headers
     * @param timeout
     * @return
     * @throws HttpComponentException
     * @throws ResponseFailedException
     * @throws PostFailedException
     * @throws UnsupportedEncodingException
     */
    public HttpResponse processHttpAppJson(RequestMethod requestMethod, String url, String content, Map<String, String> headers, Integer timeout) throws HttpComponentException, ResponseFailedException, PostFailedException, UnsupportedEncodingException, PutFailedException {
        return HttpExtractor.extractHttp(HttpComponent.sendAPIRequest(requestMethod, url,
                HttpHeader.APP_JSON.getContentType(), Constants.ENCODING_UTF8,
                content,  createContentTypeHeader(headers, HttpHeader.APP_JSON.getHeaderContentType()), timeout));
    }

    public String processHttpAppJsonSuccessResponse(RequestMethod requestMethod, String url, String content, Map<String, String> headers, Integer timeout) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getSuccessResponse(processHttpAppJson(requestMethod, url, content,  headers, timeout)) ;
    }

    public String processHttpAppJsonResponse(RequestMethod requestMethod, String url, String content, Map<String, String> headers, Integer timeout, int code) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponse(processHttpAppJson(requestMethod, url, content,  headers, timeout), code);
    }

    public String processHttpAppJsonResponseValidRange(RequestMethod requestMethod, String url, String content, Map<String, String> headers, Integer timeout, int[] validRange) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponseValidRange(processHttpAppJson(requestMethod, url, content,  headers, timeout), validRange);
    }

    public String processHttpAppJsonResponseMatchCode(RequestMethod requestMethod, String url, String content, Map<String, String> headers, Integer timeout, int[] statusCodes) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponseMatchCode(processHttpAppJson(requestMethod, url, content,  headers, timeout), statusCodes);
    }

    /**
     * Pre-defined text JSON raw request
     * @param requestMethod
     * @param url
     * @param content
     * @param headers
     * @param timeout
     * @return
     * @throws HttpComponentException
     * @throws ResponseFailedException
     * @throws PostFailedException
     * @throws UnsupportedEncodingException
     */
    public HttpResponse processHttpTextJson(RequestMethod requestMethod, String url, String content, Map<String, String> headers, Integer timeout) throws HttpComponentException, ResponseFailedException, PostFailedException, UnsupportedEncodingException, PutFailedException {
        return HttpExtractor.extractHttp(HttpComponent.sendAPIRequest(requestMethod, url,
                HttpHeader.TEXT_JSON.getContentType(), Constants.ENCODING_UTF8,
                content,  createContentTypeHeader(headers, HttpHeader.TEXT_JSON.getHeaderContentType()), timeout));
    }

    public String processHttpTextJsonSuccessResponse(RequestMethod requestMethod, String url, String content, Map<String, String> headers, Integer timeout) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getSuccessResponse(processHttpTextJson(requestMethod, url, content,  headers, timeout)) ;
    }

    public String processHttpTextJsonResponse(RequestMethod requestMethod, String url, String content, Map<String, String> headers, Integer timeout, int code) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponse(processHttpTextJson(requestMethod, url, content,  headers, timeout), code);
    }

    public String processHttpTextJsonResponseValidRange(RequestMethod requestMethod, String url, String content, Map<String, String> headers, Integer timeout, int[] validRange) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponseValidRange(processHttpTextJson(requestMethod, url, content,  headers, timeout), validRange);
    }

    public String processHttpTextJsonResponseMatchCode(RequestMethod requestMethod, String url, String content, Map<String, String> headers, Integer timeout, int[] statusCodes) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponseMatchCode(processHttpTextJson(requestMethod, url, content,  headers, timeout), statusCodes);
    }

    /**
     * Pre-defined XML raw request
     * @param requestMethod
     * @param url
     * @param content
     * @param headers
     * @param timeout
     * @return
     * @throws HttpComponentException
     * @throws ResponseFailedException
     * @throws PostFailedException
     * @throws UnsupportedEncodingException
     */
    public HttpResponse processHttpXml(RequestMethod requestMethod, String url, String content, Map<String, String> headers, Integer timeout) throws HttpComponentException, ResponseFailedException, PostFailedException, UnsupportedEncodingException, PutFailedException {
        return HttpExtractor.extractHttp(HttpComponent.sendAPIRequest(requestMethod, url,
                HttpHeader.XML.getContentType(), Constants.ENCODING_UTF8,
                content,  createContentTypeHeader(headers, HttpHeader.XML.getHeaderContentType()), timeout));
    }

    public String processHttpXmlSuccessResponse(RequestMethod requestMethod, String url, String content, Map<String, String> headers, Integer timeout) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getSuccessResponse(processHttpXml(requestMethod, url, content,  headers, timeout)) ;
    }

    public String processHttpXmlResponse(RequestMethod requestMethod, String url, String content, Map<String, String> headers, Integer timeout, int code) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponse(processHttpXml(requestMethod, url, content,  headers, timeout), code);
    }

    public String processHttpXmlResponseValidRange(RequestMethod requestMethod, String url, String content, Map<String, String> headers, Integer timeout, int[] validRange) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponseValidRange(processHttpXml(requestMethod, url, content,  headers, timeout), validRange);
    }

    public String processHttpXmlnResponseMatchCode(RequestMethod requestMethod, String url, String content, Map<String, String> headers, Integer timeout, int[] statusCodes) throws HttpComponentException, UnsupportedEncodingException, ResponseFailedException {
        return getResponseMatchCode(processHttpXml(requestMethod, url, content,  headers, timeout), statusCodes);
    }

    public static String getSuccessResponse(HttpResponse response) throws HttpComponentException {
        return HttpComponent.getSuccessResponse(response);
    }

    public static String getResponse(HttpResponse response, int code) throws HttpComponentException {
        return HttpComponent.getResponse(response, code);
    }

    public static String getResponseValidRange(HttpResponse response, int[] validRange) throws HttpComponentException {
        return HttpComponent.getResponseValidRange(response, validRange);
    }

    public static String getResponseMatchCode(HttpResponse response, int[] statusCodes) throws HttpComponentException {
        return HttpComponent.getResponseMatchCode(response, statusCodes);
    }

    protected static Map<String, String> constructParams(Map<String, Object> params){

        if(MapUtils.isNotEmpty(params)){
            Map<String, String> paramsMap = new HashMap<>();

            for(String key : params.keySet()){
                Object val = params.get(key);
                if(val != null){
                    paramsMap.put(key, val.toString());
                }
            }
            return paramsMap;
        }
        return null;
    }

    private static Map<String, String> createContentTypeHeader(Map<String, String> headers, String contentType){
        if(MapUtils.isEmpty(headers)) {
            return new HashMap<String, String>() {{
                put(Constants.HTTP_CONTENT_TYPE, contentType);
            }};
        }else{
            headers.put(Constants.HTTP_CONTENT_TYPE, contentType);
            return headers;
        }


    }

}
