package com.qding.api.call.app.qding.v2_3_0.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/8/11.
 */
@Validate
public class GetRoomsByKeyWordRequest extends BaseRequest {

    private static final long serialVersionUID = 846545807547966506L;

    @NotNullValidate
    @ExplainAnnotation(explain = "楼栋ID")
    private String buildingId;

    @ExplainAnnotation(explain = "关键字")
    private String keyWord;

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String toString() {
        return "GetRoomsByKeyWordRequest{" +
                "buildingId='" + buildingId + '\'' +
                ", keyWord='" + keyWord + '\'' +
                '}';
    }
}
