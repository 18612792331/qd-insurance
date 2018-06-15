package com.qding.api.call.app.qding.v3_3_0.struct.realestate.response;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.PackageNotice;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetPackageNoticeDtoListResponseData extends ResponseData {

    private static final long serialVersionUID = 2108010926604691864L;

    @ExplainAnnotation (explain = "入住包裹通知列表")
    private List<PackageNotice> list;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<PackageNotice> getList() {
		return list;
	}

	public void setList(List<PackageNotice> list) {
		this.list = list;
	}

	public GetPackageNoticeDtoListResponseData(List<PackageNotice> list) {
		this.list = list;
	}

	public GetPackageNoticeDtoListResponseData() {
	}
}
