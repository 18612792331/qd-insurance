package com.qding.api.call.app.qding.v2_0_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.LogisticsBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/1/7.
 */
public class LogisticsInfoResponseData extends ResponseData {

    private static final long serialVersionUID = 5352174752225487139L;

    @ExplainAnnotation(explain = "物流单号")
    private String logisticsCode;

    @ExplainAnnotation(explain = "物流公司名称")
    private String logisticsName;

    @ExplainAnnotation(explain = "配送人姓名")
    private String deliveryName;

    @ExplainAnnotation(explain = "配送人电话")
    private String deliveryMobile;

    @ExplainAnnotation(explain = "配送方式",desc = "0:快递配送显示物单号，物公司;1:自行配送，显示配送姓名，配送人电话")
    private Integer deliveryType;

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

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryMobile() {
        return deliveryMobile;
    }

    public void setDeliveryMobile(String deliveryMobile) {
        this.deliveryMobile = deliveryMobile;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    @Override
    public String toString() {
        return "LogisticsInfoResponseData{" +
                "logisticsCode='" + logisticsCode + '\'' +
                ", logisticsName='" + logisticsName + '\'' +
                ", deliveryName='" + deliveryName + '\'' +
                ", deliveryMobile='" + deliveryMobile + '\'' +
                ", deliveryType=" + deliveryType +
                ", list=" + list +
                '}';
    }
}
