package com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/11/13.
 */

@Validate
public class BindRoomByHousekeeperScanCodeRequest extends BaseRequest {

    private static final long serialVersionUID = 4268144041703761957L;

    @NotNullValidate
    private String qrcodeId;

    @NotNullValidate
    private String memberId;

    @NotNullValidate
    private String memberName;

    @NotNullValidate
    private String mobile;


    public String getQrcodeId() {
        return qrcodeId;
    }

    public void setQrcodeId(String qrcodeId) {
        this.qrcodeId = qrcodeId;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "BindRoomByHousekeeperScanCodeRequest [memeber="+memberId+",memberName=" + memberName
                + ", qrcodeId=" + qrcodeId + ", mobile=" + mobile + ", toString()="
                + super.toString() + "]";
    }
}
