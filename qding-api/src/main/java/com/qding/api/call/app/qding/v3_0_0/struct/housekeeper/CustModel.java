package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/22.
 */
public class CustModel implements Serializable {

    private static final long serialVersionUID = -8809143317538822526L;

    @ExplainAnnotation (explain = "龙湖方客户ID")
    private long custId;

    @ExplainAnnotation (explain = "客户名称")
    private String custName;

    @ExplainAnnotation (explain = "客户类型")
    private String custType;

    @ExplainAnnotation (explain = "总金额")
    private String totalFee;

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
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

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

}
