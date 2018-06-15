package com.qding.api.call.app.qding.v2_0_0.struct.platform.order;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.LogisticsBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/9/15.
 */
public class LogisticsForSubCode implements Serializable {

    private static final long serialVersionUID = -4284215930806525906L;

    @ExplainAnnotation(explain = "子订单号")
    private String subOrderCode;

    @ExplainAnnotation(explain = "物流单号")
    private String logisticsCode;

    @ExplainAnnotation(explain = "物流公司名称")
    private String logisticsName;

    @ExplainAnnotation(explain = "订单跟踪记录")
    private List<LogisticsBean> list;


    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public List<LogisticsBean> getList() {
        return list;
    }

    public void setList(List<LogisticsBean> list) {
        this.list = list;
    }

    public String getSubOrderCode() {
        return subOrderCode;
    }

    public void setSubOrderCode(String subOrderCode) {
        this.subOrderCode = subOrderCode;
    }
}
