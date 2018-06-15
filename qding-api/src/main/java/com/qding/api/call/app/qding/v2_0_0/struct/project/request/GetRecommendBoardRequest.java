package com.qding.api.call.app.qding.v2_0_0.struct.project.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2015/12/30.
 */

@Validate
public class GetRecommendBoardRequest extends BaseRequest {

    private static final long serialVersionUID = -2504275824753987040L;

    @NotNullValidate
    @ExplainAnnotation(explain = "社区ID")
    private String projectId;

    @ExplainAnnotation(explain = "会员ID")
    private String memberId;

    @MinValueValidate(value="1")
    @ExplainAnnotation(explain = "当前查询页码")
    private int pageNo = 1;

    @RangeValueValidate(max="20", min="1")
    @ExplainAnnotation(explain = "每页显示数")
    private int pageSize = 10;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
