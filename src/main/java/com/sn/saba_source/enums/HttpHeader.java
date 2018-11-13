package com.sn.saba_source.enums;

public enum HttpHeader {

    APP_JSON("application/json", "application/json;charset=UTF-8"),
    TEXT_JSON("text/json", "text/json;charset=UTF-8"),
    XML("text/xml", "text/xml;charset=UTF-8");

    String contentType;
    String headerContentType;

    HttpHeader(String contentType, String headerContentType){
        this.contentType = contentType;
        this.headerContentType = headerContentType;
    }

    public String getContentType() {
        return contentType;
    }

    public String getHeaderContentType() {
        return headerContentType;
    }
}
