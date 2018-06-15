package com.qding.api.call.app.qding.v2_3_0.struct.hotcycle.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/27.
 */

@Validate
public class JoinGroupApplyRequest extends BaseRequest {

    private static final long serialVersionUID = 1819105300301309141L;

    @NotNullValidate
    @ExplainAnnotation (explain = "群组ID")
    private  String gcRoomId;

    @NotNullValidate
    @ExplainAnnotation (explain = "操作者ID")
    private String userId;

    @NotNullValidate
    @ExplainAnnotation (explain = "操作者名称")
    private String optName;

    // TODO: 2016/6/6 因IOS对此字段没有进行赋值，所以业务逻辑需要通过userId来获取 
    @ExplainAnnotation (explain = "申请者会员ID")
    private String memberId;

    // TODO: 2016/6/6 因IOS对此字段没有进行赋值，所以业务逻辑需要通过gcRoomId来获取
    @ExplainAnnotation (explain = "申请者当前所在社区")
    private String projectId;

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
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

    public String getGcRoomId() {
        return gcRoomId;
    }

    public void setGcRoomId(String gcRoomId) {
        this.gcRoomId = gcRoomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "JoinGroupApplyRequest{" +
                "gcRoomId='" + gcRoomId + '\'' +
                ", userId='" + userId + '\'' +
                ", optName='" + optName + '\'' +
                ", memberId='" + memberId + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
