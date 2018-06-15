package com.qding.api.call.app.qding.v1_4_1.struct.project;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/11/3.
 */
public class GrouponActivity implements Serializable {

    private static final long serialVersionUID = 4537205751463285340L;

    private String id;
    private String shortDesc;
    private Long startTime;
    private Long endTime;
    private List<String> imagez;
    private Integer num;
    private String desc;
    private List<ProductSalePolicyBean> policies;
    private String url;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public List<String> getImagez() {
        return imagez;
    }

    public void setImagez(List<String> imagez) {
        this.imagez = imagez;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ProductSalePolicyBean> getPolicies() {
        return policies;
    }

    public void setPolicies(List<ProductSalePolicyBean> policies) {
        this.policies = policies;
    }
}
