package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;

import java.util.List;

/**
 * Created by qd on 2015/12/29.
 */
@Validate
public class AssociateKeyWordRequest extends BaseRequest {

    private static final long serialVersionUID = 6646637451253286862L;

    @ExplainAnnotation(explain = "社区ID")
    private String projectId;

    @ExplainAnnotation(explain = "关键词")
    private String kw;

    @ExplainAnnotation(explain = "待显示条数")
    private int limit = 10;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }


}
