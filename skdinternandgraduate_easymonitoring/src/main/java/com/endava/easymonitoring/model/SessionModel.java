package com.endava.easymonitoring.model;

import java.util.Date;
public class SessionModel {

    private String sessionId;
    private Date date=new Date();

    public SessionModel(){}

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
