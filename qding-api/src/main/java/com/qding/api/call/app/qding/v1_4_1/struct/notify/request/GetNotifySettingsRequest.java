package com.qding.api.call.app.qding.v1_4_1.struct.notify.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/11/2.
 */
@Validate
public class GetNotifySettingsRequest extends BaseRequest {

    private static final long serialVersionUID = 3860017754267900086L;

    /**
     * 会员ID
     */
    @NotNullValidate
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }


    @Override
    public String toString() {
        return "GetNotifySettingsRequest [memberId="+memberId+",toString()="+super.toString()+" ]";
    }

}
