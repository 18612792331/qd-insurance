package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/9/9.
 */
@Validate
public class AddPraiseRequest extends BaseRequest {

    private static final long serialVersionUID = -689322614785848275L;

    @ExplainAnnotation (explain = "话题ID")
    @NotNullValidate
    private String topicId;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "AddPraiseRequest{" +
                "topicId='" + topicId + '\'' +
                '}';
    }
}
