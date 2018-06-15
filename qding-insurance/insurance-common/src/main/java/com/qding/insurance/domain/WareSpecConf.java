package com.qding.insurance.domain;

import java.io.Serializable;

public class WareSpecConf implements Serializable{

    private static final long serialVersionUID = -3111262551427765728L;

    private String id;

    private String wareId;

    private String specValueCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWareId() {
        return wareId;
    }

    public void setWareId(String wareId) {
        this.wareId = wareId == null ? null : wareId.trim();
    }

    public String getSpecValueCode() {
        return specValueCode;
    }

    public void setSpecValueCode(String specValueCode) {
        this.specValueCode = specValueCode == null ? null : specValueCode.trim();
    }

}