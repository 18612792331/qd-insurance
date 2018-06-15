package com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.Notice;
import com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.NoticeTypeInfoBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetProjectNoticeResponseData extends ResponseData{


	private static final long serialVersionUID = -1165260432081781669L;

	@ExplainAnnotation(explain = "公告列表头名称")
	private String title;

	@ExplainAnnotation(explain = "当前记录数")
	private int recordCount;

	@ExplainAnnotation(explain = "总记录数")
	private int totalCount;

	private List<NoticeTypeInfoBean> noticeTypeList;
	
	private List<Notice> list;

	public List<NoticeTypeInfoBean> getNoticeTypeList() {
		return noticeTypeList;
	}

	public void setNoticeTypeList(List<NoticeTypeInfoBean> noticeTypeList) {
		this.noticeTypeList = noticeTypeList;
	}

	public GetProjectNoticeResponseData(int recordCount, int totalCount, List<NoticeTypeInfoBean> noticeTypeList, List<Notice> list) {
		this.recordCount = recordCount;
		this.totalCount = totalCount;
		this.noticeTypeList = noticeTypeList;
		this.list = list;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public GetProjectNoticeResponseData() {
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

	public List<Notice> getList() {
		return list;
	}

	public void setList(List<Notice> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetProjectNoticeResponseData{" +
				"title='" + title + '\'' +
				", recordCount=" + recordCount +
				", totalCount=" + totalCount +
				", noticeTypeList=" + noticeTypeList +
				", list=" + list +
				'}';
	}
}
