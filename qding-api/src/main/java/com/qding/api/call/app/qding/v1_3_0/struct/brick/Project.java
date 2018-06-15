package com.qding.api.call.app.qding.v1_3_0.struct.brick;

import java.io.Serializable;
import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="project")
public class Project implements Serializable{


	private static final long serialVersionUID = -6265796743635324301L;

	@ExplainAnnotation (desc = "社区ID")
	private String id;

	@ExplainAnnotation (desc = "社区名称")
	private String name;

	@ExplainAnnotation (desc = "社区所属城市")
	private String cityId;

	@ExplainAnnotation (desc = "社区所属城市名称")
	private String cityName;

	@ExplainAnnotation (desc = "与当前用户距离")
	private String distance;

	@ExplainAnnotation (desc = "社区联系信息列表")
	private List<ProjectConcat> concats;

	@ExplainAnnotation (desc = "社区分区列表")
	private List<Group> groups;

	@ExplainAnnotation (explain = "是否是演示社区",desc = "1:是，0:否")
	private Integer isDemo = Integer.valueOf(0);

	@ExplainAnnotation (desc = "所属省ID")
	private Long provinceId;

	@ExplainAnnotation (desc = "所属省名称")
	private String provinceName;

	@ExplainAnnotation (desc = "所属区县ID")
	private Long districtId;

	@ExplainAnnotation (desc = "所属区县名称")
	private String districtName;

	@ExplainAnnotation (desc = "所属街道信息")
	private String streetInfo;

	@ExplainAnnotation (explain = "是否支持租售业务",desc = "0:支持，1:支持")
	private int isRent;

	@ExplainAnnotation (explain = "物业云|千丁社区关联ID")
	private String csmProjectId;

	@ExplainAnnotation (explain = "同步社区ID")
	private String syncProjectId;

	public String getCsmProjectId() {
		return csmProjectId;
	}

	public void setCsmProjectId(String csmProjectId) {
		this.csmProjectId = csmProjectId;
	}

	public String getSyncProjectId() {
		return syncProjectId;
	}

	public void setSyncProjectId(String syncProjectId) {
		this.syncProjectId = syncProjectId;
	}

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

	public int getIsRent() {
		return isRent;
	}

	public void setIsRent(int isRent) {
		this.isRent = isRent;
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

	public String getStreetInfo() {
		return streetInfo;
	}

	public void setStreetInfo(String streetInfo) {
		this.streetInfo = streetInfo;
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
				", streetInfo='" + streetInfo + '\'' +
				", isRent=" + isRent +
				", csmProjectId='" + csmProjectId + '\'' +
				", syncProjectId='" + syncProjectId + '\'' +
				'}';
	}
}
