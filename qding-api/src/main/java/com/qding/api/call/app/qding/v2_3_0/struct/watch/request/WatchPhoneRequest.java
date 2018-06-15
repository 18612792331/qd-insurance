package com.qding.api.call.app.qding.v2_3_0.struct.watch.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_3_0.struct.watch.OtherPhoneInfo;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.List;


@Validate
public class WatchPhoneRequest extends BaseRequest {

    private static final long serialVersionUID = 365015804037843637L;

    @ExplainAnnotation(explain = "会员ID")
    @NotNullValidate
    private String memberId;

    @ExplainAnnotation(explain = "手表设备码")
    @NotNullValidate
    private String imei;

    @ExplainAnnotation(explain = "手表电话号码")
    private String watchPhone;

    @ExplainAnnotation(explain = "情亲号码")
    @NotNullValidate
    private List<OtherPhoneInfo> otherPhoneList;

    @ExplainAnnotation(explain = "操作类型", desc = "1:设置; 2:修改")
    @NotNullValidate
    private String type;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getWatchPhone() {
        return watchPhone;
    }

    public void setWatchPhone(String watchPhone) {
        this.watchPhone = watchPhone;
    }

    public List<OtherPhoneInfo> getOtherPhoneList() {
        return otherPhoneList;
    }

    public void setOtherPhoneList(List<OtherPhoneInfo> otherPhoneList) {
        this.otherPhoneList = otherPhoneList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "WatchPhoneRequest{" +
                "memberId='" + memberId + '\'' +
                ", imei='" + imei + '\'' +
                ", watchPhone='" + watchPhone + '\'' +
                ", otherPhoneList=" + otherPhoneList +
                ", type='" + type + '\'' +
                '}';
    }
}
