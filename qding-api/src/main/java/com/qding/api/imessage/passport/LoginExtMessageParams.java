package com.qding.api.imessage.passport;

import java.io.Serializable;

/**
 * Created by qd on 2017/5/3.
 */
public class LoginExtMessageParams implements Serializable {

    private static final long serialVersionUID = -2302639533730867281L;

    private String accountId;

    private String mobile;

    private String qdDevice;

    private String qdPlatform;

    private String osVersion;

    private String qdVersion;

    private Integer type;

    private String deviceNo;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQdDevice() {
        return qdDevice;
    }

    public void setQdDevice(String qdDevice) {
        this.qdDevice = qdDevice;
    }

    public String getQdPlatform() {
        return qdPlatform;
    }

    public void setQdPlatform(String qdPlatform) {
        this.qdPlatform = qdPlatform;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getQdVersion() {
        return qdVersion;
    }

    public void setQdVersion(String qdVersion) {
        this.qdVersion = qdVersion;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "LoginExtMessageParams{" +
                "accountId='" + accountId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", qdDevice='" + qdDevice + '\'' +
                ", qdPlatform='" + qdPlatform + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", qdVersion='" + qdVersion + '\'' +
                ", type=" + type +
                ", deviceNo='" + deviceNo + '\'' +
                '}';
    }
}
