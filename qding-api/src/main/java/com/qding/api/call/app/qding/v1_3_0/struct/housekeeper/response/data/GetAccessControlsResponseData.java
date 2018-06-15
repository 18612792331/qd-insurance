package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.AccessControl;
import com.qding.api.struct.ResponseData;

/**
 * 根据用户id和社区id查询门禁列表					
 * @author lichao
 *
 */
public class GetAccessControlsResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1196573014379190442L;

	private int recordCount;
	
	private int totalCount;
	
	private List<AccessControl> list;
	
	public GetAccessControlsResponseData() {
	}

	public GetAccessControlsResponseData(int recordCount, int totalCount,
			List<AccessControl> list) {
		super();
		this.recordCount = recordCount;
		this.totalCount = totalCount;
		this.list = list;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<AccessControl> getList() {
		return list;
	}

	public void setList(List<AccessControl> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetAccessControlsResponseData [recordCount=" + recordCount
				+ ", totalCount=" + totalCount + ", list=" + list
				+ ", toString()=" + super.toString() + "]";
	}
	
}
