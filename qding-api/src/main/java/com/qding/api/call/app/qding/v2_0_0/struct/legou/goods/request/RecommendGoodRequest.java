package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2016/1/5.
 */
@Validate
public class RecommendGoodRequest extends BaseRequest {

    private static final long serialVersionUID = 6382840037043991078L;

    @MinValueValidate(value="1")
    @ExplainAnnotation(explain = "当前查询页码")
    private Integer pageNo = 1;

    @RangeValueValidate(max="20", min="1")
    @ExplainAnnotation(explain = "每页查询条数")
    private Integer pageSize = 10;

    @NotNullValidate
    @ExplainAnnotation(explain = "社区ID")
    private String projectId;

    @ExplainAnnotation(explain = "会员ID")
    private String memberId;

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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "RecommendGoodRequest{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", projectId='" + projectId + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
