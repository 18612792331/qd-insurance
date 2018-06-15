package com.qding.api.call.app.qding.v3_0_1.struct.groupon.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by jinhaishan on 17/4/14.
 */
@Validate
public class GrouponDetailRequest extends BaseRequest{

    private static final long serialVersionUID = -496009501296573833L;

    @NotNullValidate
    @ExplainAnnotation(explain = "团购ID")
    private Long id;

    @ExplainAnnotation(explain = "社区ID")
    private Long projectId;

    @ExplainAnnotation(explain = "是否只需要关键信息", desc = "默认true，不返回轮播图，图文详情")
    private boolean curt = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public boolean isCurt() {
        return curt;
    }

    public void setCurt(boolean curt) {
        this.curt = curt;
    }
}
