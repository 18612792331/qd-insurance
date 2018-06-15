package com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectSpell;
import com.qding.api.struct.ResponseData;

/**
 * 根据城市id获取社区列表					
 * @author lichao
 *
 */
public class GetProjectByCityIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3493956198494562955L;

	private List<ProjectSpell> list;
	
	public GetProjectByCityIdResponseData() {

	}

	public GetProjectByCityIdResponseData(List<ProjectSpell> list) {
		super();
		this.list = list;
	}

	public List<ProjectSpell> getList() {
		return list;
	}

	public void setList(List<ProjectSpell> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetProjectByCityIdResponseData [list=" + list + ", toString()="
				+ super.toString() + "]";
	}
	
	
} 
