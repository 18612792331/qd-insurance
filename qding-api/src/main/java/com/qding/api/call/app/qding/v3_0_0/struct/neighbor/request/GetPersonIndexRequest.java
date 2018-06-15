package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2017/2/24.
 */
public class GetPersonIndexRequest extends BaseRequest {

    private static final long serialVersionUID = -1717672458567290355L;

    @ExplainAnnotation (explain = "被访问者ID")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GetPersonIndexRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
