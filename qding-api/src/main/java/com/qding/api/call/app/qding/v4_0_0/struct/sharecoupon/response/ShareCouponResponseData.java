package com.qding.api.call.app.qding.v4_0_0.struct.sharecoupon.response;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v4_0_0.struct.sharecoupon.ShareCouponDTO;
import com.qding.api.call.app.qding.v4_0_0.struct.sharecoupon.SkuDTO;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by xuxiaoxing on 2018/5/3.
 */
public class ShareCouponResponseData extends ResponseData {

    @ExplainAnnotation(explain = "拼团信息")
    private ShareCouponDTO shareCouponDTO;

    @ExplainAnnotation(explain="活动货品列表")
    private List<SkuDTO> skuDTOList;


    public List<SkuDTO> getSkuDTOList() {
        return skuDTOList;
    }

    public void setSkuDTOList(List<SkuDTO> skuDTOList) {
        this.skuDTOList = skuDTOList;
    }

    public ShareCouponDTO getShareCouponDTO() {
        return shareCouponDTO;
    }

    public void setShareCouponDTO(ShareCouponDTO shareCouponDTO) {
        this.shareCouponDTO = shareCouponDTO;
    }
}
