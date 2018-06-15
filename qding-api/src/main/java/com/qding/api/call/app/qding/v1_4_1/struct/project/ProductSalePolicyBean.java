package com.qding.api.call.app.qding.v1_4_1.struct.project;

import java.io.Serializable;

/**
 * Created by qd on 2015/11/3.
 */
public class ProductSalePolicyBean implements Serializable {

    private static final long serialVersionUID = -4278524932825697678L;
    private String productId;
    private Integer fromNum;
    private Integer toNum;
    private Long price;
    private Integer status;
    private String desc;
    private Integer stage;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getFromNum() {
        return fromNum;
    }

    public void setFromNum(Integer fromNum) {
        this.fromNum = fromNum;
    }

    public Integer getToNum() {
        return toNum;
    }

    public void setToNum(Integer toNum) {
        this.toNum = toNum;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
