package com.qding.api.call.app.qding.v2_5_0.struct.brick;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/12/13.
 */
public class DomainDTO implements Serializable {

    private static final long serialVersionUID = 5437707327852979403L;

    @ExplainAnnotation(explain = "一级域名")
    private String domain;

    @ExplainAnnotation(explain = "子级域名")
    private String subDomain;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSubDomain() {
        return subDomain;
    }

    public void setSubDomain(String subDomain) {
        this.subDomain = subDomain;
    }

    @Override
    public String toString() {
        return "DomainDTO{" +
                "domain='" + domain + '\'' +
                ", subDomain='" + subDomain + '\'' +
                '}';
    }
}
