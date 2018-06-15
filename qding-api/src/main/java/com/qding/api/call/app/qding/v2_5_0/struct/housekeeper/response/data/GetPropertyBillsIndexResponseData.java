package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.PropertyBills;
import com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.OwnerInfo;
import com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.PropertyBillsInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetPropertyBillsIndexResponseData extends ResponseData {

    /**
     *
     */
    private static final long serialVersionUID = 6806038544150193190L;

    @ExplainAnnotation(explain = "当前记录条数")
    private int recordCount;

    @ExplainAnnotation(explain = "总记录数")
    private int totalCount;

    @ExplainAnnotation(explain = "总欠费金额")
    private String sumDebt;

    @ExplainAnnotation(explain = "总预付费金额")
    private String sumPrePay;

    @ExplainAnnotation(explain = "是否绑定过当前房屋")
    private boolean bind;

    @ExplainAnnotation(explain = "物业账单列表")
    private List<PropertyBillsInfo> list;

    @ExplainAnnotation(explain = "业主信息列表")
    private List<OwnerInfo> ownerInfos;

    public List<OwnerInfo> getOwnerInfos() {
        return ownerInfos;
    }

    @ExplainAnnotation(explain = "1:清账阶段 0：非清账阶段",desc = "3.0版本统一修正为：0:清账阶段 1：非清账阶段")
    private int canPayFee;

    @ExplainAnnotation(explain = "提示信息")
    private String remindMsg;

    @ExplainAnnotation(explain = "应付金额")
    private String shouldPay;

    @ExplainAnnotation(explain = "抵扣金额")
    private String discountAmount;

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

    public void setOwnerInfos(List<OwnerInfo> ownerInfos) {
        this.ownerInfos = ownerInfos;
    }

    public boolean isBind() {
        return bind;
    }

    public void setBind(boolean bind) {
        this.bind = bind;
    }

    public GetPropertyBillsIndexResponseData() {

    }


    public String getSumDebt() {
        return sumDebt;
    }

    public void setSumDebt(String sumDebt) {
        this.sumDebt = sumDebt;
    }

    public String getSumPrePay() {
        return sumPrePay;
    }

    public void setSumPrePay(String sumPrePay) {
        this.sumPrePay = sumPrePay;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCanPayFee() {
        return canPayFee;
    }

    public void setCanPayFee(int canPayFee) {
        this.canPayFee = canPayFee;
    }

    public List<PropertyBillsInfo> getList() {
        return list;
    }

    public void setList(List<PropertyBillsInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetPropertyBillsIndexResponseData{" +
                "recordCount=" + recordCount +
                ", totalCount=" + totalCount +
                ", sumDebt='" + sumDebt + '\'' +
                ", sumPrePay='" + sumPrePay + '\'' +
                ", bind=" + bind +
                ", list=" + list +
                ", ownerInfos=" + ownerInfos +
                ", canPayFee=" + canPayFee +
                ", remindMsg='" + remindMsg + '\'' +
                ", shouldPay='" + shouldPay + '\'' +
                ", discountAmount='" + discountAmount + '\'' +
                '}';
    }
}
