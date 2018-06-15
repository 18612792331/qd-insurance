package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.FeeModel;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.SumFee;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/3/22.
 */
public class GetReplaceFeeResponseData extends ResponseData {

    private static final long serialVersionUID = -2109931759277061178L;

    private SumFee sumFee;
    private Long custId;
    private String custName;
    private String custType;
    @ExplainAnnotation (explain = "是否允许缴费")
    private Integer canPayFee;
    private String remindMsg;
    private String shouldPay;
    private String discountAmount;
    private List<FeeModel> feeModelList;


    public SumFee getSumFee() {
        return sumFee;
    }

    public void setSumFee(SumFee sumFee) {
        this.sumFee = sumFee;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public Integer getCanPayFee() {
        return canPayFee;
    }

    public void setCanPayFee(Integer canPayFee) {
        this.canPayFee = canPayFee;
    }

    public String getRemindMsg() {
        return remindMsg;
    }

    public void setRemindMsg(String remindMsg) {
        this.remindMsg = remindMsg;
    }

    public String getShouldPay() {
        return shouldPay;
    }

    public void setShouldPay(String shouldPay) {
        this.shouldPay = shouldPay;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public List<FeeModel> getFeeModelList() {
        return feeModelList;
    }

    public void setFeeModelList(List<FeeModel> feeModelList) {
        this.feeModelList = feeModelList;
    }


    @Override
    public String toString() {
        return "GetReplaceFeeResponseData{" +
                "sumFee=" + sumFee +
                ", custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custType='" + custType + '\'' +
                ", canPayFee=" + canPayFee +
                ", remindMsg='" + remindMsg + '\'' +
                ", shouldPay='" + shouldPay + '\'' +
                ", discountAmount='" + discountAmount + '\'' +
                ", feeModelList=" + feeModelList +
                '}';
    }
}
