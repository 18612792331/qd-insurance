package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2016/9/9.
 */
@Validate
public class GetBriefThemesByPagingRequest extends BaseRequest {

    private static final long serialVersionUID = 7569096417964736614L;

    @ExplainAnnotation (explain = "社区ID")
    @NotNullValidate
    private String projectId;

    @ExplainAnnotation (explain = "会员ID")
    @NotNullValidate
    private String memberId;


    @ExplainAnnotation (explain = "当前请求页码")
    @MinValueValidate(value="1")
    private Integer pageNo;

    @ExplainAnnotation (explain = "每页显示数")
    @RangeValueValidate(max="100", min="1")
    private Integer pageSize = Integer.valueOf(100);

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

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    @Override
    public String toString() {
        return "GetBriefThemesByPagingRequest{" +
                "projectId='" + projectId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
