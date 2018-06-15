package com.qding.api.call.app.qding.v2_4_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/1/8.
 */
public class LogisticsInfo implements Serializable {

    private static final long serialVersionUID = -7731055947910209050L;

    @ExplainAnnotation(explain = "物流单号")
    private String logisticsCode = "";

    @ExplainAnnotation(explain = "物流公司名称|自行配送人姓名")
    private String companyName ="";

    @ExplainAnnotation(explain = "物流公司编号")
    private String companyCode = "";

    @ExplainAnnotation(explain = "包裹名称")
    private String parcelName="";

    @ExplainAnnotation(explain = "商品图片缩略图集合")
    private List<String> imgList;

    @ExplainAnnotation(explain = "最新物流信息")
    private String lastLogisticsInfo;

    @ExplainAnnotation(explain = "当前包裹商品购买数")
    private Integer goodsBuyCount;

    @ExplainAnnotation(explain = "子订单号")
    private List<String> subOrderCodes;

    public Integer getGoodsBuyCount() {
        return goodsBuyCount;
    }

    public void setGoodsBuyCount(Integer goodsBuyCount) {
        this.goodsBuyCount = goodsBuyCount;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getParcelName() {
        return parcelName;
    }

    public void setParcelName(String parcelName) {
        this.parcelName = parcelName;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public String getLastLogisticsInfo() {
        return lastLogisticsInfo;
    }

    public void setLastLogisticsInfo(String lastLogisticsInfo) {
        this.lastLogisticsInfo = lastLogisticsInfo;
    }

    public List<String> getSubOrderCodes() {
        return subOrderCodes;
    }

    public void setSubOrderCodes(List<String> subOrderCodes) {
        this.subOrderCodes = subOrderCodes;
    }
}
