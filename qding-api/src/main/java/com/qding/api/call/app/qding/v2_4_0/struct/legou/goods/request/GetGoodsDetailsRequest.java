package com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/6/16.
 */
@Validate
public class GetGoodsDetailsRequest extends BaseRequest {

    private static final long serialVersionUID = -406200047631047150L;

    @ExplainAnnotation(explain = "社区ID")
    private Long projectId;

    @NotNullValidate
    @ExplainAnnotation(explain = "货品ID")
    private Long skuId;

    @ExplainAnnotation(explain = "当前会员ID")
    private String memberId;

    @ExplainAnnotation(explain = "是否查询购买量", desc = "需要:true,不需要:false")
    private boolean findSellNum = true;

    @ExplainAnnotation(explain = "是否查询库存数", desc = "需要:true,不需要:false")
    private boolean findSkuStock = true;

    @ExplainAnnotation(explain = "该功能是否用于分享信息展示", desc = "是:true,否:false")
    private boolean showShare = false;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public boolean getFindSellNum() {
        return findSellNum;
    }

    public void setFindSellNum(boolean findSellNum) {
        this.findSellNum = findSellNum;
    }

    public boolean getFindSkuStock() {
        return findSkuStock;
    }

    public void setFindSkuStock(boolean findSkuStock) {
        this.findSkuStock = findSkuStock;
    }

    public boolean getShowShare() {
        return showShare;
    }

    public void setShowShare(boolean showShare) {
        this.showShare = showShare;
    }

    @Override
    public String toString() {
        return "GetGoodsDetailsRequest{" +
                "projectId=" + projectId +
                ", skuId=" + skuId +
                ", memberId='" + memberId + '\'' +
                ", findSellNum=" + findSellNum +
                ", findSkuStock=" + findSkuStock +
                ", showShare=" + showShare +
                '}';
    }
}


