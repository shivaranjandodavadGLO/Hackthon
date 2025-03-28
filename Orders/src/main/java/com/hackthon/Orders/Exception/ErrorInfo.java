package com.hackthon.Orders.Exception;

import java.time.LocalDateTime;

public class ErrorInfo {
    private String errormessage;
    private String HttpMessage;
    private LocalDateTime localDateTime;

    public ErrorInfo(String errormessage, String httpMessage, LocalDateTime localDateTime) {
        this.errormessage = errormessage;
        HttpMessage = httpMessage;
        this.localDateTime = localDateTime;
    }

    public ErrorInfo() {
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    public String getHttpMessage() {
        return HttpMessage;
    }

    public void setHttpMessage(String httpMessage) {
        HttpMessage = httpMessage;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
                "errormessage='" + errormessage + '\'' +
                ", HttpMessage='" + HttpMessage + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
