package com.qding.api.controllers;

/**
 * Created by qd on 2016/3/1.
 */
public class ApiMethodInfo {

    private String explain;

    private String version;

    private String serviceAlias;

    private String methodAlias;

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServiceAlias() {
        return serviceAlias;
    }

    public void setServiceAlias(String serviceAlias) {
        this.serviceAlias = serviceAlias;
    }

    public String getMethodAlias() {
        return methodAlias;
    }

    public void setMethodAlias(String methodAlias) {
        this.methodAlias = methodAlias;
    }
}
