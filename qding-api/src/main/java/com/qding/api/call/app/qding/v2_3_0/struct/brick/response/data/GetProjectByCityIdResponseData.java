package com.qding.api.call.app.qding.v2_3_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.City;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Project;
import com.qding.api.call.app.qding.v2_3_0.struct.brick.ProjectsByGroup;
import com.qding.api.struct.ResponseData;
import com.qding.brick.pojo.biz.Region;

import java.util.List;

/**
 * 根据城市id获取社区列表					
 * @author lichao
 *
 */
public class GetProjectByCityIdResponseData extends ResponseData{

	private static final long serialVersionUID = 3493956198494562955L;

	@ExplainAnnotation (explain = "社区分组列表")
	private List<ProjectsByGroup> list;

	@ExplainAnnotation (explain = "当前所定位社区信息")
	private Project locateProject;

	@ExplainAnnotation (explain = "展示方式",desc = "1:首字母分组,2:地理位置")
	private Integer showType;

	@ExplainAnnotation (explain = "展示方式",desc = "1:正常,2:城市不同步，已开启社区服务(gps城市),3:城市不同步，未开启社区服务(gps城市)")
	private Integer status;

	@ExplainAnnotation (explain = "经GPS定位的城市")
	private City city;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Integer getShowType() {
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	public GetProjectByCityIdResponseData() {

	}

	public GetProjectByCityIdResponseData(List<ProjectsByGroup> list) {
		super();
		this.list = list;
	}

	public List<ProjectsByGroup> getList() {
		return list;
	}

	public void setList(List<ProjectsByGroup> list) {
		this.list = list;
	}

	public Project getLocateProject() {
		return locateProject;
	}

	public void setLocateProject(Project locateProject) {
		this.locateProject = locateProject;
	}

	@Override
	public String toString() {
		return "GetProjectByCityIdResponseData{" +
				"list=" + list +
				", locateProject=" + locateProject +
				", showType=" + showType +
				'}';
	}
}
