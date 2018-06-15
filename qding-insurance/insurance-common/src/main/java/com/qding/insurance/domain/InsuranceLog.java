package com.qding.insurance.domain;

import java.io.Serializable;
import java.util.Date;

public class InsuranceLog implements Serializable{
    private static final long serialVersionUID = -7198741382370159478L;

    private String id;

    private Integer logType;

    private String objId;

    private String operate;

    private String operateBy;

    private Date operateAt;

    private String operateBefore;

    private String operateAfter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId == null ? null : objId.trim();
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate == null ? null : operate.trim();
    }

    public String getOperateBy() {
        return operateBy;
    }

    public void setOperateBy(String operateBy) {
        this.operateBy = operateBy == null ? null : operateBy.trim();
    }

    public Date getOperateAt() {
        return operateAt;
    }

    public void setOperateAt(Date operateAt) {
        this.operateAt = operateAt;
    }

    public String getOperateBefore() {
        return operateBefore;
    }

    public void setOperateBefore(String operateBefore) {
        this.operateBefore = operateBefore == null ? null : operateBefore.trim();
    }

    public String getOperateAfter() {
        return operateAfter;
    }

    public void setOperateAfter(String operateAfter) {
        this.operateAfter = operateAfter == null ? null : operateAfter.trim();
    }
}