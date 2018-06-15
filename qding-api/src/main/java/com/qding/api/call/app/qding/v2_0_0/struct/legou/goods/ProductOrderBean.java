package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/1/5.
 */
public class ProductOrderBean  implements Serializable {


    private static final long serialVersionUID = 1074449351883406435L;

    @ExplainAnnotation(explain = "订单号")
    private String orderCode;

    @ExplainAnnotation(explain = "订单状态",desc = "根据具体业态而定")
    private Integer orderStatus;

    @ExplainAnnotation(explain = "订单状态名称")
    private String statusName;

    @ExplainAnnotation(explain = "订单标题")
    private String title;

    @ExplainAnnotation(explain = "订单描述")
    private String desc;

    @ExplainAnnotation(explain = "图片列表")
    private String[] imgs;

    @ExplainAnnotation(explain = "url跳转")
    private List<BtnSkip> btnSkipList;

    public List<BtnSkip> getBtnSkipList() {
        return btnSkipList;
    }

    public void setBtnSkipList(List<BtnSkip> btnSkipList) {
        this.btnSkipList = btnSkipList;
    }

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
