package com.qding.api.call.app.qding.v2_1_0.struct.hotcycle.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/27.
 */
@Validate
public class ShareHotcycleHomePageRequest extends BaseRequest {

    private static final long serialVersionUID = 1674400993460831856L;

    @NotNullValidate
    @ExplainAnnotation(explain = "当前社区ID")
    private String projectId;

    @NotNullValidate
    @ExplainAnnotation(explain = "AccountID")
    private String userId;

    @NotNullValidate
    @ExplainAnnotation(explain = "page size")
    private Integer pageSize;

    @NotNullValidate
    @ExplainAnnotation(explain = "页码")
    private Integer PageNo;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return PageNo;
    }

    public void setPageNo(Integer pageNo) {
        PageNo = pageNo;
    }

    @Override
    public String toString() {
        return "ShareHotcycleHomePageRequest{" +
                "projectId='" + projectId + '\'' +
                ", userId='" + userId + '\'' +
                ", pageSize=" + pageSize +
                ", PageNo=" + PageNo +
                '}';
    }
}
