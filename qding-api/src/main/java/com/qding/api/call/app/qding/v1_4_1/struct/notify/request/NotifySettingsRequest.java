package com.qding.api.call.app.qding.v1_4_1.struct.notify.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/28.
 */
@Validate
public class NotifySettingsRequest  extends BaseRequest {

    private static final long serialVersionUID = 3818756814328640010L;

    /**
     * 会员ID
     */
    @NotNullValidate
    private String memberId;

    /**
     * 消息类型
     */
    @NotNullValidate
    private String notifyType; // 1:订单消息  2：系统消息

    /**
     * 消息设置值
     */
    @NotNullValidate
    private Boolean settingValue = true;


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public Boolean getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(Boolean settingValue) {
        this.settingValue = settingValue;
    }

    @Override
    public String toString() {
        return "NotifySettingsRequest [memberId="+memberId+",notifyType="+notifyType+",settingValue="+settingValue+",toString()="+super.toString()+" ]";
    }

}
