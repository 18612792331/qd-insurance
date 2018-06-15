package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import lombok.core.configuration.ExampleValueString;

/**
 * Created by qd on 2016/1/9.
 */

@Validate
public class GetGcRoomInfoRequest extends BaseRequest {

    private static final long serialVersionUID = 2829206035337446814L;

    /**
     * 群组ID
     */
    @NotNullValidate
    @ExplainAnnotation(explain = "群组ID")
    private String gcRoomId;

    public String getGcRoomId() {
        return gcRoomId;
    }

    public void setGcRoomId(String gcRoomId) {
        this.gcRoomId = gcRoomId;
    }

    @Override
    public String toString() {
        return "GetGcRoomInfoRequest{" +
                "gcRoomId='" + gcRoomId + '\'' +
                '}';
    }
}
