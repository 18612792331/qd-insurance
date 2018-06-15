package com.qding.api.call.app.qding.v2_5_0.struct.brick;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Group;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;

@XStreamAlias(value = "project")
public class Project implements Serializable {


    private static final long serialVersionUID = -6265796743635324301L;

    @ExplainAnnotation(desc = "社区ID")
    private String id;

    @ExplainAnnotation(desc = "社区名称")
    private String name;

    @ExplainAnnotation(desc = "社区所属城市")
    private String cityId;

    @ExplainAnnotation(desc = "社区所属城市名称")
    private String cityName;

    @ExplainAnnotation(desc = "与当前用户距离")
    private String distance;

    @ExplainAnnotation(desc = "社区联系信息列表")
    private List<ProjectConcat> concats;

    @ExplainAnnotation(desc = "社区分区列表")
    private List<Group> groups;

    @ExplainAnnotation(explain = "是否是演示社区", desc = "1:是，0:否")
    private Integer isDemo = Integer.valueOf(0);

    @ExplainAnnotation(desc = "所属省ID")
    private Long provinceId;

    @ExplainAnnotation(desc = "所属省名称")
    private String provinceName;

    @ExplainAnnotation(desc = "所属区县ID")
    private Long districtId;

    @ExplainAnnotation(desc = "所属区县名称")
    private String districtName;

    @ExplainAnnotation(desc = "所属街道信息")
    private String street;
    
    @ExplainAnnotation(desc = "1：有组团或每个组团需要独立指定街道地址，0：无组团或无需为每个组团独立指定街道地址")
    private int isGroupAddress;

    public Integer getIsDemo() {
        return isDemo;
    }

    public void setIsDemo(Integer isDemo) {
        this.isDemo = isDemo;
    }

    public Project() {
    }

    public Project(String id, String name, String cityId, String cityName, String distance, List<ProjectConcat> concats, List<Group> groups) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
        this.cityName = cityName;
        this.distance = distance;
        this.concats = concats;
        this.groups = groups;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<ProjectConcat> getConcats() {
        return concats;
    }

    public void setConcats(List<ProjectConcat> concats) {
        this.concats = concats;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    
    public int getIsGroupAddress() {
		return isGroupAddress;
	}

	public void setIsGroupAddress(int isGroupAddress) {
		this.isGroupAddress = isGroupAddress;
	}

	@Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cityId='" + cityId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", distance='" + distance + '\'' +
                ", concats=" + concats +
                ", groups=" + groups +
                ", isDemo=" + isDemo +
                ", provinceId=" + provinceId +
                ", provinceName='" + provinceName + '\'' +
                ", districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                ", street='" + street + '\'' +
                ", isGroupAddress='" + isGroupAddress + '\'' +
                '}';
    }
}
