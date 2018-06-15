package com.qding.api.call.app.qding.v2_0_0.struct.project.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2016/7/6.
 */
@Validate
public class GetProjectAroundInfoRequest extends BaseRequest {

    private static final long serialVersionUID = 6260829050268159086L;

    @NotNullValidate
    @ExplainAnnotation (explain = "社区ID")
    private Long projectId;

    @NotNullValidate
    @ExplainAnnotation (explain = "周边商户类型")
    private String aroundTypeCode;

    @MinValueValidate(value="1")
    @ExplainAnnotation(explain = "当前查询页码")
    private int pageNo = 1;

    @RangeValueValidate(max="20", min="1")
    @ExplainAnnotation(explain = "每页查询数")
    private int pageSize = 10;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getAroundTypeCode() {
        return aroundTypeCode;
    }

    public void setAroundTypeCode(String aroundTypeCode) {
        this.aroundTypeCode = aroundTypeCode;
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

    @Override
    public String toString() {
        return "GetProjectAroundInfoRequest{" +
                "projectId=" + projectId +
                ", aroundTypeCode='" + aroundTypeCode + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
