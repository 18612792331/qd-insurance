package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MaxValueValidate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/1/5.
 */
@Validate
public class GetWareCommentListRequest extends BaseRequest {

    private static final long serialVersionUID = -2708757431805592140L;

    @NotNullValidate
    @ExplainAnnotation(explain = "商品ID")
    private Long  wareId;

    @MinValueValidate(value="1")
    @ExplainAnnotation(explain = "当前查询页码")
    private Integer pageNo = 1;

    @MaxValueValidate(value="20")
    @ExplainAnnotation(explain = "每页查询数")
    private Integer pageSize = 10;

    @ExplainAnnotation(explain = "社区ID")
    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
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
        return "GetWareCommentListRequest{" +
                "wareId=" + wareId +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }

}
