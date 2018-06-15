package com.qding.api.call.app.qding.v2_5_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Addresses;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.OrderGroup;

/**
 * Created by qd on 2016/8/25.
 */
public class PropertySelfOrderGroup extends OrderGroup {

    private static final long serialVersionUID = -9186657180376990009L;

    @ExplainAnnotation (explain = "含有周先生商品的温馨提示文案")
    private String remindContent = "";

    @ExplainAnnotation (explain = "是否包含周先生商品",desc = "0:未包含，1：包含")
    private Integer containZxs =0;


    public String getRemindContent() {
        return remindContent;
    }

    public void setRemindContent(String remindContent) {
        this.remindContent = remindContent;
    }

    public Integer getContainZxs() {
        return containZxs;
    }

    public void setContainZxs(Integer containZxs) {
        this.containZxs = containZxs;
    }

}
