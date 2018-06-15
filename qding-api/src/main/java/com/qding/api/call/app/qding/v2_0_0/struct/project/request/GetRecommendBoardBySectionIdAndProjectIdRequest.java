package com.qding.api.call.app.qding.v2_0_0.struct.project.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2016/1/18.
 */
@Validate
public class GetRecommendBoardBySectionIdAndProjectIdRequest extends BaseRequest {

    private static final long serialVersionUID = 3732148227767367117L;

    @NotNullValidate
    @ExplainAnnotation(explain = "社区ID")
    private String projectId;

    @NotNullValidate
    @ExplainAnnotation(explain = "板块ID",desc = "1:亲子,2:旅游,3:生鲜,4:特色,5:活动,6:楼层")
    private Integer sectionId;


    @MinValueValidate(value="1")
    @ExplainAnnotation(explain = "当前页码",desc = "预留字段，暂不分页")
    private Integer pageNo = 1;

    @RangeValueValidate(max="20", min="1")
    @ExplainAnnotation(explain = "每页查询显示数",desc = "预留字段，暂不分页")
    private Integer pageSize = 10;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
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
}
