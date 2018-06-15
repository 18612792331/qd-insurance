package com.qding.insurance.param;

import java.io.Serializable;
import java.util.List;

import com.qding.insurance.domain.InsuranceSku;
import com.qding.insurance.vo.GuaranteeItemVo;

public class SaveSkuAndGuaranteeParam implements Serializable {

    private static final long serialVersionUID = -7275592118437958837L;

    private String wareId;

    // 该商品配置的可选规格，英文逗号分隔
    private String specCodes;

    private List<InsuranceSku> skuList;

    private List<GuaranteeItemVo> guaranteeItemList;

    public String getWareId() {
        return wareId;
    }

    public void setWareId(String wareId) {
        this.wareId = wareId;
    }

    public String getSpecCodes() {
        return specCodes;
    }

    public void setSpecCodes(String specCodes) {
        this.specCodes = specCodes;
    }

    public List<InsuranceSku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<InsuranceSku> skuList) {
        this.skuList = skuList;
    }

    public List<GuaranteeItemVo> getGuaranteeItemList() {
        return guaranteeItemList;
    }

    public void setGuaranteeItemList(List<GuaranteeItemVo> guaranteeItemList) {
        this.guaranteeItemList = guaranteeItemList;
    }

}
