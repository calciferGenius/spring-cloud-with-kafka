package com.sn.saba_source.utils.http;/**
 * Created by pc-001 on 9/4/18.
 */

/**
 * Created by Kier pc-001 on 9/4/18.
 */

public class HttpResponse {

    String methodName;

    Integer statusCode;

    String response;


    public HttpResponse(String methodName, Integer statusCode, String response) {
        this.methodName = methodName;
        this.statusCode = statusCode;
        this.response = response;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "methodName=" + methodName +
                ", statusCode=" + statusCode +
                ", response='" + response + '\'' +
                '}';
    }
}
