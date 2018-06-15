package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.PropertyBillsByMonthInfo;
import com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.PropertyFeeOrderInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetFeeOrderListResponseData extends ResponseData {

    private static final long serialVersionUID = 6806038544150193190L;

    @ExplainAnnotation(explain = "1:清账阶段 0：非清账阶段")
    private int canPayFee;

    @ExplainAnnotation(explain = "提示信息")
    private String remindMsg;

    @ExplainAnnotation(explain = "是否绑定过当前房屋")
    private boolean bind;

    @ExplainAnnotation(explain = "缴费记录列表")
    private List<PropertyFeeOrderInfo> list;

    @ExplainAnnotation(explain = "总记录数")
    private int totalCount;

    public List<PropertyFeeOrderInfo> getList() {
        return list;
    }

    public void setList(List<PropertyFeeOrderInfo> list) {
        this.list = list;
    }

    public int getCanPayFee() {
        return canPayFee;
    }

    public void setCanPayFee(int canPayFee) {
        this.canPayFee = canPayFee;
    }

    public String getRemindMsg() {
        return remindMsg;
    }

    public void setRemindMsg(String remindMsg) {
        this.remindMsg = remindMsg;
    }

    public boolean isBind() {
        return bind;
    }

    public void setBind(boolean bind) {
        this.bind = bind;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "GetFeeOrderListResponseData{" +
                "canPayFee=" + canPayFee +
                ", remindMsg='" + remindMsg + '\'' +
                ", bind=" + bind +
                ", list=" + list +
                ", totalCount=" + totalCount +
                '}';
    }
}
