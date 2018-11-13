package com.sn.saba_source.utils.http;/**
 * Created by pc-001 on 9/4/18.
 */

import java.util.Map;

/**
 * Created by Kier pc-001 on 9/4/18.
 */

public class HttpRequest {

    String raw;
    Map<String, String> params;
    String contentType;
    String encoding;

    public HttpRequest(String raw) {
        this.raw = raw;
    }

    public HttpRequest(Map<String, String> params) {
        this.params = params;
    }

    public HttpRequest(String raw, String contentType, String encoding) {
        this.raw = raw;
        this.contentType = contentType;
        this.encoding = encoding;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "raw='" + raw + '\'' +
                ", params=" + params +
                ", contentType='" + contentType + '\'' +
                ", encoding='" + encoding + '\'' +
                '}';
    }
}
