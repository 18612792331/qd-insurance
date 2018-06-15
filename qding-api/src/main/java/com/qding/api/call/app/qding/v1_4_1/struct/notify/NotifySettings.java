package com.qding.api.call.app.qding.v1_4_1.struct.notify;

import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.io.Serializable;

/**
 * Created by qd on 2015/11/2.
 */
public class NotifySettings implements Serializable {

    private static final long serialVersionUID = 2384491073408029837L;


    /**
     * 订单消息设定
     */
    @NotNullValidate
    private Boolean orderMsg= true;

    /**
     * 系统消息设定
     */
    @NotNullValidate
    private Boolean sysMsg= true;

    public Boolean getOrderMsg() {
        return orderMsg;
    }

    public void setOrderMsg(Boolean orderMsg) {
        this.orderMsg = orderMsg;
    }

    public Boolean getSysMsg() {
        return sysMsg;
    }

    public void setSysMsg(Boolean sysMsg) {
        this.sysMsg = sysMsg;
    }
}
