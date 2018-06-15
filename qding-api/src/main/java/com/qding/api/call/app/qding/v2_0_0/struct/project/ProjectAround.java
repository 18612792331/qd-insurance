package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import lombok.core.configuration.ExampleValueString;

import java.io.Serializable;

/**
 * Created by qd on 2016/7/6.
 */
public class ProjectAround implements Serializable {

    private static final long serialVersionUID = -8598061024678705204L;

    @ExplainAnnotation (explain = "ID")
    private String id;

    @ExplainAnnotation (explain = "标签")
    private String tag;

    @ExplainAnnotation (explain = "名称")
    private String name;

    @ExplainAnnotation (explain = "类型")
    private String type;

    @ExplainAnnotation (explain = "标签Code")
    private String typecode;

    @ExplainAnnotation (explain = "地址")
    private String address;

    @ExplainAnnotation (explain = "坐标")
    private String location;

    @ExplainAnnotation (explain = "电话")
    private String tel;

    @ExplainAnnotation (explain = "邮编")
    private String postcode;

    @ExplainAnnotation (explain = "网址")
    private String website;

    @ExplainAnnotation (explain = "省编码")
    private String pcode;

    @ExplainAnnotation (explain = "省名称")
    private String pname;

    @ExplainAnnotation (explain = "城市编码")
    private String citycode;

    @ExplainAnnotation (explain = "城市名称")
    private String cityname;

    @ExplainAnnotation (explain = "区县编码")
    private String adcode;

    @ExplainAnnotation (explain = "区县名称")
    private String adname;

    @ExplainAnnotation (explain = "商业圈")
    private String businessArea;

    @ExplainAnnotation (explain = "照片")
    private String photos;

    @ExplainAnnotation (explain = "距离")
    private String distance;

    @ExplainAnnotation (explain = "扩展属性")
    private String bizExt;

    @ExplainAnnotation (explain = "更新时间")
    private Long updateAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getAdname() {
        return adname;
    }

    public void setAdname(String adname) {
        this.adname = adname;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getBizExt() {
        return bizExt;
    }

    public void setBizExt(String bizExt) {
        this.bizExt = bizExt;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }


}
