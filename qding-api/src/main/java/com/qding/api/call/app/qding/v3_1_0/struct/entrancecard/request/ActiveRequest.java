package com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class ActiveRequest extends BaseRequest {

    private static final long serialVersionUID = 5311022661356402582L;

    @ExplainAnnotation(explain = "门禁卡号，多个卡号以英文逗号分隔")
    @NotNullValidate
    private String cardNOs;

    @ExplainAnnotation(explain = "用户当前所选社区ID")
    @NotNullValidate
    private String projectId;

    public String getCardNOs() {
        return cardNOs;
    }

    public void setCardNOs(String cardNOs) {
        this.cardNOs = cardNOs;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

}
