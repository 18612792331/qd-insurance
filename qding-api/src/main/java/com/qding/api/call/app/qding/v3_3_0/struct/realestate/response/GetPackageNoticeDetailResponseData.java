package com.qding.api.call.app.qding.v3_3_0.struct.realestate.response;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.PackageNotice;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.PackageNoticeDetail;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetPackageNoticeDetailResponseData extends ResponseData {

    private static final long serialVersionUID = 2108010926604691864L;

    @ExplainAnnotation (explain = "入住包裹通知详情")
    private PackageNoticeDetail packageNoticeDetail;

	public PackageNoticeDetail getPackageNoticeDetail() {
		return packageNoticeDetail;
	}

	public void setPackageNoticeDetail(PackageNoticeDetail packageNoticeDetail) {
		this.packageNoticeDetail = packageNoticeDetail;
	}

	public GetPackageNoticeDetailResponseData(PackageNoticeDetail packageNoticeDetail) {
		this.packageNoticeDetail = packageNoticeDetail;
	}

	public GetPackageNoticeDetailResponseData() {
	}
}
