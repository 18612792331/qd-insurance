package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2016/9/9.
 */
public class BriefMember extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 8933812459335545530L;

    @ExplainAnnotation (explain = "会员ID")
    private String memberId = "";

    @ExplainAnnotation (explain = "会员头像")
    private String memberAvatar= "";

    @ExplainAnnotation (explain = "会员名称")
    private String memberName= "";

    @ExplainAnnotation (explain = "所属账户ID")
    private String userId = "";

    @ExplainAnnotation (explain = "会员签名")
    private String memberSignature = "";

    @ExplainAnnotation (explain = "1:邻聚小助手 2:会员")
    private String memberType;

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberAvatar() {
        return memberAvatar;
    }

    public void setMemberAvatar(String memberAvatar) {
        this.memberAvatar = memberAvatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMemberSignature() {
        return memberSignature;
    }

    public void setMemberSignature(String memberSignature) {
        this.memberSignature = memberSignature;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
