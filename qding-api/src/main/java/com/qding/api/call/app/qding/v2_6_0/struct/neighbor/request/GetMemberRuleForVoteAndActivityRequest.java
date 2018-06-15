package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/9/13.
 */
@Validate
public class GetMemberRuleForVoteAndActivityRequest extends BaseRequest {

    private static final long serialVersionUID = 2918533679713331824L;

    @ExplainAnnotation (explain = "主题ID")
    @NotNullValidate
    private String themeId;

    @ExplainAnnotation (explain = "会员ID")
    @NotNullValidate
    private String memberId;

    @ExplainAnnotation (explain = "当前社区ID")
    @NotNullValidate
    private String projectId;

    public GetMemberRuleForVoteAndActivityRequest() {
    }

    public GetMemberRuleForVoteAndActivityRequest(String themeId, String memberId, String projectId) {
        this.themeId = themeId;
        this.memberId = memberId;
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "GetMemberRuleForVoteAndActivityRequest{" +
                "themeId='" + themeId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
