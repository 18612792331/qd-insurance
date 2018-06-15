package com.qding.api.call.app.qding.v3_0_0.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.List;

@Validate
public class GetRoomByCmsIdRequest extends BaseRequest {

    private static final long serialVersionUID = 1423568248115033857L;

    @NotNullValidate
    @ExplainAnnotation(explain = "千丁|物业云房间关联ID")
    private List<String> csmIds;

    public List<String> getCsmIds() {
        return csmIds;
    }

    public void setCsmIds(List<String> csmIds) {
        this.csmIds = csmIds;
    }

    @Override
    public String toString() {
        return "GetRoomByCmsIdRequest{" +
                "csmIds=" + csmIds +
                '}';
    }
}
