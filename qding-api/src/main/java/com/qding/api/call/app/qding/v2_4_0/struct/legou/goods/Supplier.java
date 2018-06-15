package com.qding.api.call.app.qding.v2_4_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.Date;


public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExplainAnnotation(explain = "id")
    private Long id;

    @ExplainAnnotation(explain = "供方类型", desc = "1:洗衣 | 2:团购 | 3:旅游 | 4:物业")
    private String type;

    @ExplainAnnotation(explain = "全称")
    private String fullName;

    @ExplainAnnotation(explain = "简称")
    private String shortName;

    @ExplainAnnotation(explain = "0 禁用  1 启用")
    private Integer flag;

    @ExplainAnnotation(explain = "地址")
    private String address;

    @ExplainAnnotation(explain = "手机号")
    private String mobile;

    @ExplainAnnotation(explain = "联系人")
    private String contactor;

    @ExplainAnnotation(explain = "证照信息")
    private String license;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", flag=" + flag +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", contactor='" + contactor + '\'' +
                ", license='" + license + '\'' +
                '}';
    }
}
