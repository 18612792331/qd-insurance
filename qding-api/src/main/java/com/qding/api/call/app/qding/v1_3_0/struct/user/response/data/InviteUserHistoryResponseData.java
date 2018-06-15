package com.qding.api.call.app.qding.v1_3_0.struct.user.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.user.Invitation;
import com.qding.api.struct.ResponseData;

public class InviteUserHistoryResponseData   extends ResponseData {

	
	private static final long serialVersionUID = 8556113766780843433L;

	public InviteUserHistoryResponseData() {
	
	}
	
	/**
     * 邀请信息对象列表
     */
	private List<Invitation> list;
	
	/**
     * 当前查询记录数
     */
	private int recordCount;
	
	/**
     * 总记录数
     */
	private int totalCount;

	

	public List<Invitation> getList() {
		return list;
	}

	public void setList(List<Invitation> list) {
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

	public InviteUserHistoryResponseData(List<Invitation> list, int recordCount,
			int totalCount) {
		super();
		this.list = list;
		this.recordCount = recordCount;
		this.totalCount = totalCount;
	}
	
	@Override
    public String toString() {
        return "InviteUserHistoryResponseData [list="+list+",recordCount="+recordCount+",totalCount="+totalCount+",toString()=" + super.toString() + "]";
    }
	

}
