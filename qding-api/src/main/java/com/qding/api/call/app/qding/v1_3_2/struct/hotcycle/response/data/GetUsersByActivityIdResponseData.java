package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.response.data;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.User;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * 根据活动id返回此活动的用户列表，带分页
 * @author jiawenzheng
 *
 */
public class GetUsersByActivityIdResponseData extends ResponseData{

	/**
	 *
	 */
	private static final long serialVersionUID = -5412843848804175988L;


	/**
	 * 总记录数
	 */
	private int totalCount;

	/**
	 * 当前页返回记录数
	 */
	private int recordCount;

	/**
	 * 用户列表
	 */
	private List<User> list;

	public GetUsersByActivityIdResponseData() {

	}

	public GetUsersByActivityIdResponseData(int totalCount, int recordCount,
											List<User> list) {
		super();
		this.totalCount = totalCount;
		this.recordCount = recordCount;
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public List<User> getList() {
		return list;
	}
	
	public void setList(List<User> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetUsersByActivityIdResponseData [totalCount=" + totalCount
				+ ", recordCount=" + recordCount + ", list=" + list
				+ ", toString()=" + super.toString() + "]";
	}
}
