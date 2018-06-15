package com.qding.api.call.app.qding.v2_3_0.struct.watch.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_3_0.struct.watch.OtherPhoneInfo;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.List;

/**
 * Created by qd on 2016/5/16.
 */
@Validate
public class UpdateWatchOtherPhoneRequest extends BaseRequest {

    private static final long serialVersionUID = 1415169027090931098L;

    @ExplainAnnotation(explain = "会员ID")
    @NotNullValidate
    private String memberId;

    @ExplainAnnotation(explain = "手表设备号")
    @NotNullValidate
    private String imei;

    @ExplainAnnotation(explain = "亲情手机号")
    @NotNullValidate
    private List<OtherPhoneInfo> otherPhoneList;


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public List<OtherPhoneInfo> getOtherPhoneList() {
        return otherPhoneList;
    }

    public void setOtherPhoneList(List<OtherPhoneInfo> otherPhoneList) {
        this.otherPhoneList = otherPhoneList;
    }

    @Override
    public String toString() {
        return "UpdateWatchOtherPhoneRequest{" +
                "memberId='" + memberId + '\'' +
                ", imei='" + imei + '\'' +
                ", otherPhoneList=" + otherPhoneList +
                '}';
    }
}
