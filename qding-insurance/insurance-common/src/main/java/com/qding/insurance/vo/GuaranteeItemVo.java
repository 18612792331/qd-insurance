package com.qding.insurance.vo;

import java.util.List;

import com.qding.insurance.domain.GuaranteeItem;
import com.qding.insurance.domain.GuaranteeObject;

public class GuaranteeItemVo extends GuaranteeItem {

    private List<GuaranteeObject> guaranteeObjectList;

    public List<GuaranteeObject> getGuaranteeObjectList() {
        return guaranteeObjectList;
    }

    public void setGuaranteeObjectList(List<GuaranteeObject> guaranteeObjectList) {
        this.guaranteeObjectList = guaranteeObjectList;
    }

}
