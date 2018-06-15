package com.qding.api.call.app.qding.v2_3_0.struct.watch.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/5/16.
 */

@Validate
public class BindWatchRequest extends BaseRequest {

    private static final long serialVersionUID = -1255996456049271650L;

    @NotNullValidate
    @ExplainAnnotation(explain = "绑定人memberId")
    private String memberId = "";

    @NotNullValidate
    @ExplainAnnotation(explain = "绑定人accoutnId")
    private String accountId = "";


    @NotNullValidate
    @ExplainAnnotation(explain = "被绑定手表串号")
    private String imei = "";


    @NotNullValidate
    @ExplainAnnotation(explain = "绑定人角色", desc = "1:妈妈; 2:爸爸; 3:爷爷; 4:奶奶; 5:外公; 6:外婆; 7:其他亲属;")
    private String role;


    @ExplainAnnotation(explain = "手表nickname")
    private String nickName = "";


    @ExplainAnnotation(explain = "手表头像")
    private String headImage = "";

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    @Override
    public String toString() {
        return "BindWatchByAccountRequest{" +
                "memberId='" + memberId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", imei='" + imei + '\'' +
                ", role=" + role +
                ", nickName='" + nickName + '\'' +
                ", headImage='" + headImage + '\'' +
                '}';
    }
}
