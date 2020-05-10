package com.lijiajie.wynbolg.wynblog.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ActionLog {
    private int logId;
    private String logAction;
    private String logError;
    private Date logDate;

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getLogAction() {
        return logAction;
    }

    public void setLogAction(String logAction) {
        this.logAction = logAction;
    }

    public String getLogError() {
        return logError;
    }

    public void setLogError(String logError) {
        this.logError = logError;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
}
